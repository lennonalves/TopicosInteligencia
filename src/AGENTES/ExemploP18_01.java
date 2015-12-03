/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AGENTES;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author lennon
 */
public class ExemploP18_01 extends Agent{
    @Override
    public void setup() {
        System.out.println("Iniciando agente " + getAID().getName());
        addBehaviour(new MessageReceiverBehaviour());
        addBehaviour(new TickerBehaviour(this, 1000) {
            @Override
            protected void onTick() {
                System.out.println("Tick executado");
            }
        });
    }
    
    private class MessageReceiverBehaviour extends CyclicBehaviour {
        @Override
        public void action() {
            ACLMessage msg = blockingReceive();
            System.out.println("\nAgente " + getLocalName()
            + " mensagem recebida de " + msg.getSender().getName() + ": "
            + msg.getContent());
        }
    }
}
