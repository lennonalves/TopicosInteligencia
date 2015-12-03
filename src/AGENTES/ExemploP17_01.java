/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AGENTES;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author lennon
 */

public class ExemploP17_01 extends Agent {
    
    @Override
    protected void setup() {
        addBehaviour(new MessageSenderBehaviour());
    }
    
    private class MessageSenderBehaviour extends OneShotBehaviour {
        
        @Override
        public void action() {
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setLanguage("myLanguage");
            msg.setContent("olá");
            msg.addReceiver(new AID("rma#localhost:1099/JADE", false));
            send(msg);
            System.out.println("Agente: " + getAID().getName() + " - Mensagem enviada");
            doDelete();
        }
    }
    
    @Override
    public void takeDown() {
        System.out.println("Agente: " + getAID().getName() + " - Finalizando agente");
    }
}