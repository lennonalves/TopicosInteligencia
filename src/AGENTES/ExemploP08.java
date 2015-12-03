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

public class ExemploP08 extends Agent {
    
    @Override
    public void setup() {
        System.out.println("Criando Agente " + getAID().getName());
        addBehaviour(new MyBehaviour());
    }
    
    private class MyBehaviour extends Behaviour {
        private int passo = 0;
        
        @Override
        public void action() {
            switch (passo) {
                case 1:
                    System.out.println("Passo 01: " + passo); break;
                case 2:
                    System.out.println("Passo 01: " + passo); break;
                case 3:
                    System.out.println("Passo 01: " + passo); break;
                case 4:
                    System.out.println("Passo 01: " + passo); break;
                case 5:
                    System.out.println("Passo 01: " + passo); break;
            }
            passo ++;
        }
        
        @Override
        public boolean done() {
            return passo == 4;
        }
    }
}
