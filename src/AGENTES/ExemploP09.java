/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AGENTES;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

/**
 *
 * @author lennon
 */

public class ExemploP09 extends Agent {
    
    @Override
    public void setup() {
        addBehaviour(new Behaviour() {
            
            @Override
            public void action() {
                System.out.println("Início guloso");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Fim guloso");
            }
            
            @Override
            public boolean done() {
                return false;
            }
        });
        
        addBehaviour(new Behaviour() {
            
            @Override
            public void action() {
                System.out.println("Passando fome...");
            }
            
            @Override
            public boolean done() {
                return false;
            }
        });
    }
}
