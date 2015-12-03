/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Comando usado para inicializar os agentes:
 * ac = cc.createNewAgent("Agente1", "aula26.mensagens.AgentePapudo", new String[]{"Agente2"});
 * ac.start();
 * ac = cc.createNewAgent("Agente2", "aula26.mensagens.AgentePapudo", new String[]{"Agente1"});
 * ac.start();
 */
package AGENTES;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author lennon
 */
public class TrocaDeMensagens extends Agent {
    
    private String destinatario;
    
    protected void setup() {
        
        System.out.println("Configurando..." + getAID().getName());
        this.destinatario = (String) getArguments()[0];
        addBehaviour (new processarMensagens());
    }

    private class processarMensagens extends CyclicBehaviour {

        @Override
        public void action() {
            ACLMessage msg = receive();
            if (msg != null) {
                System.out.println("\nAgente " + getLocalName()
                        + " mensagem recebida de " + msg.getSender().getName() + ": "
                        + " conteúdo: " + msg.getContent());
                
                ACLMessage resp = new ACLMessage(ACLMessage.INFORM);
                resp.addReceiver(new AID(destinatario, false));
                resp.setContent("Oi, meu nome é " + getLocalName());
                send(resp);
                System.out.println("Agente: " + getAID().getName() + " - Mensagem enviada para "
                        + msg.getSender().getName() + " conteudo " + resp.getContent());
            } else {
                msg = new ACLMessage(ACLMessage.INFORM);
                msg.setContent("Como vai você?");
                AID aid = new AID(destinatario, false);
                msg.addReceiver(aid);
                msg.addReplyTo(getAID());
                send(msg);
                System.out.println("Primeira mensagem do agente: " + getAID().getName() + " - enviada para " + aid.getName());
                
                block();
            }
        }
    }
}
