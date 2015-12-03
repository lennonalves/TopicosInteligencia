/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AGENTES;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author lennon
 */
public class ExemploP17_02 extends Agent {
    public void setup() {
        System.out.println("Iniciando agente " + getAID().getName());
        addBehaviour(new MessageReceiverBehaviour());
    }
    
    public class MessageReceiverBehaviour extends CyclicBehaviour {
        public void action() {
            ACLMessage msg = receive();
            if (msg != null) {
                System.out.println("\nAgente " + getLocalName()
                + " mensagem recebida de " + msg.getSender().getName() + ": "
                + " conteúdo: " + msg.getContent());
            } else {
                block();
            }
        }
    }
}
