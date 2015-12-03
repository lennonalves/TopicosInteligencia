/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AGENTES;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 *
 * @author lennon
 */
public class ExemploP19_01 extends Agent{
    @Override
    public void setup() {
        System.out.println("Iniciando agente " + getAID().getName());
        addBehaviour(new SpecificMessageReceiverBehaviour());
        addBehaviour(new AllMessageReceiverBehaviour());
    }
    
    private class SpecificMessageReceiverBehaviour extends CyclicBehaviour {
        @Override
        public void action() {
            MessageTemplate m1 = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
            MessageTemplate m2 = MessageTemplate.MatchLanguage("myLanguage");
            MessageTemplate m1andm2 = MessageTemplate.and(m1, m2);
            ACLMessage msg = receive(m1andm2);
            if (msg != null) {
                System.out.println("\nAgente: " + getLocalName() + " mensagem específica recebida de "
                + msg.getSender().getName() + ": " + msg.getContent());
            } else { block(); }
        }
    }
    
    private class AllMessageReceiverBehaviour extends CyclicBehaviour {
        public void action() {
            ACLMessage msg = receive();
            if (msg != null) {
                System.out.println("\nAgente " + getLocalName()
                + " mensagem qualquer recebida de " + msg.getSender().getName() + ": "
                + msg.getContent());
            } else { block(); }
        }
    }
}
