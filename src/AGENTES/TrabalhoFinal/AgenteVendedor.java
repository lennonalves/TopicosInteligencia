/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AGENTES.TrabalhoFinal;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.*;

/**
 *
 * @author lennon
 */
public class AgenteVendedor extends Agent {
    private Hashtable catalogo;
    private vendaGUI vGUI;
    
    @Override
    protected void setup() {
        catalogo = new Hashtable(); /* cria um novo catálogo */
        
        vGUI = new vendaGUI(this);
        vGUI.show(); /* abre interface de insercao de produtos */
        
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("negociacao");
        sd.setName("JADE-comercio");
        dfd.addServices(sd);
        
        try {
            DFService.register(this, dfd);
        } catch (FIPAException fe) {}
        
        addBehaviour(new ofertas()); /* servidor de pedidos de ofertas */
        addBehaviour(new vendas()); /* servidor de ordem de compra */
    }
    
    @Override
    protected void takeDown() { /* termina o agente vendedor */
        try {
            DFService.deregister(this);
        } catch (FIPAException fe) {}
        
        vGUI.dispose();
        System.out.println("O agente " + getAID().getName() + " foi terminado.");
    }
    
    public void atualizaCatalogo(final String produto, final int preco) { /* inclui produto ao catálogo */
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                catalogo.put(produto, preco);
                System.out.println(produto + " inserido ao catálogo. Preço: $" + preco);
            }
        });
    }
    
    private class ofertas extends CyclicBehaviour { /* recebe proposta de um agente comprador */
        @Override
        public void action() {
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
            ACLMessage msg = myAgent.receive(mt);
            if (msg != null) {
                String produto = msg.getContent();
                ACLMessage resposta = msg.createReply();
                
                Integer preco = (Integer) catalogo.get(produto);
                if (preco != null) {
                    resposta.setPerformative(ACLMessage.PROPOSE);
                    resposta.setContent(String.valueOf(preco.intValue()));
                } else {
                    resposta.setPerformative(ACLMessage.REFUSE);
                    resposta.setContent("indisponivel");
                }
                
                myAgent.send(resposta);
            } else {
                block();
            }
        }
    }
    
    private class vendas extends CyclicBehaviour { /* vende o produto ao agente comprador */
        @Override
        public void action() {
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
            ACLMessage msg = myAgent.receive(mt);
            if (msg != null) {
                String produto = msg.getContent();
                ACLMessage resposta = msg.createReply();
                Integer preco = (Integer) catalogo.remove(produto);
                if (preco != null) {
                    resposta.setPerformative(ACLMessage.INFORM);
                    System.out.println(produto + " vendido ao comprador " + msg.getSender().getName());
                } else {
                    resposta.setPerformative(ACLMessage.FAILURE);
                    resposta.setContent("indisponivel");
                }
                myAgent.send(resposta);
            } else {
                block();
            }
        }
    }
}
