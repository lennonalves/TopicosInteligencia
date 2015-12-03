/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AGENTES.TrabalhoFinal;

import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


/**
 *
 * @author lennon
 */
public class AgenteComprador extends Agent {
    
    private String produto; /* nome/atributo do produto */
    private AID[] vendedores; /* lista de vendedores */
    
    @Override
    protected void setup() {
        System.out.println("O agente " + getAID().getName() + " entrou na negociação."); /* mensagem de boas vindas */
        
        Object[] args = getArguments(); /* nome/atributo do produto */
        if (args != null && args.length > 0) {
            produto = (String) args[0];
            System.out.println("Produto desejado: " + produto); /* argumento recebido */
            
            addBehaviour(new TickerBehaviour(this, 60000 /* tempo de espera para inicio da negociacao */) {
                @Override
                protected void onTick() {
                    System.out.println("Tentando comprar " + produto + ".");
                    
                    DFAgentDescription template = new DFAgentDescription();
                    ServiceDescription sd = new ServiceDescription();
                    sd.setType("negociacao");
                    template.addServices(sd);
                    try {
                        DFAgentDescription[] resultado = DFService.search(myAgent, template);
                        System.out.println("Vendedores encontrados: ");
                        vendedores = new AID[resultado.length];
                        for (int i = 0; i < resultado.length; ++i) {
                            vendedores[i] = resultado[i].getName();
                            System.out.println(vendedores[i].getName() + ".");
                        }
                    } catch (FIPAException fe) {
                    }
                    myAgent.addBehaviour(new RequestPerformer());
                }
            });
        } else {
            System.out.println("O produto não está disponível.");
            doDelete(); /* termina o agente */
        }
    }
    
    @Override
    protected void takeDown() {
        System.out.println("O agente " + getAID().getName() + " saiu da negociação."); /* mensagem de término */
    }
    
    private class RequestPerformer extends Behaviour {
        
        private AID melhorVendedor; /* registra vendedor com melhor preço */
        private int melhorPreco; /* registra melhor preço */
        private int respostas = 0; /* contador de respostas */
        private MessageTemplate mt;
        private int passo = 0;
        
        @Override
        public void action() {
            switch (passo) {
                case 0: /* localiza vendedores do produto desejado */
                    ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
                    for (int i = 0; i < vendedores.length; ++i) {
                        cfp.addReceiver(vendedores[i]);
                    }
                    cfp.setContent(produto);
                    cfp.setConversationId("venda");
                    cfp.setReplyWith("cfp"+System.currentTimeMillis()); /* valor único */
                    myAgent.send(cfp);
                    
                    mt = MessageTemplate.and(MessageTemplate.MatchConversationId("venda"), 
                            MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
                    passo = 1;
                    break;
                case 1: /* busca por melhor preço entre os vendedores encontrados */
                    ACLMessage resposta = myAgent.receive(mt);
                    if (resposta != null) {
                        if (resposta.getPerformative() == ACLMessage.PROPOSE) {
                            int preco = Integer.parseInt(resposta.getContent());
                            if (melhorVendedor == null || preco < melhorPreco) {
                                melhorPreco = preco;
                                melhorVendedor = resposta.getSender();
                            }
                        }
                        respostas++;
                        if (respostas >= vendedores.length) {
                            passo = 2;
                        }
                    } else {
                        block();
                    }
                    break;
                case 2: /* envia uma oferta ao vendedor de melhor preço */
                    ACLMessage encomenda = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                    encomenda.addReceiver(melhorVendedor);
                    encomenda.setContent(produto);
                    encomenda.setConversationId("venda");
                    encomenda.setReplyWith("encomenda" + System.currentTimeMillis());
                    myAgent.send(encomenda);
                    
                    mt = MessageTemplate.and(MessageTemplate.MatchConversationId("venda"),
                            MessageTemplate.MatchInReplyTo(encomenda.getReplyWith()));
                    passo = 3;
                    break;
                case 3:  /* realiza a compra */
                    resposta = myAgent.receive(mt);
                    if (resposta != null) {
                        if (resposta.getPerformative() == ACLMessage.INFORM) {
                            System.out.println(produto + " foi comprado(a) com sucesso do vendedor "
                            + resposta.getSender().getName());
                            System.out.println("Preço do produto: $" + melhorPreco);
                            myAgent.doDelete();
                        } else {
                            System.out.println("A tentativa de compra falhou. Produto já vendido.");
                        }
                        passo = 4;
                    } else {
                        block();
                    }
                    break;
            }
        }
        
        @Override
        public boolean done() { /* não existe o produto desejado ou produto desejado já vendido */
            if (passo == 2 && melhorVendedor == null) {
                System.out.println("A tentativa de compra falhou. " + produto + " não disponível para compra.");
            }
            return ((passo == 2 && melhorVendedor == null) || passo == 4);
        }
    }
}
