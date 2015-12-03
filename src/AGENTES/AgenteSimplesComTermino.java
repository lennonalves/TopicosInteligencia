/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AGENTES;

import jade.core.Agent;

/**
 *
 * @author lennon
 */

public class AgenteSimplesComTermino extends Agent {
    
    @Override
    protected void setup() {
        System.out.println("Agente " + getAID().getName() + " entrou no chat.");
        doDelete();
    }
    
    @Override
    protected void takeDown() {
        System.out.println("Agente " + getAID().getName() + " saiu do chat.");
    }
}