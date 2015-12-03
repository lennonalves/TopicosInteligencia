/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AGENTES;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

/**
 *
 * @author lennon
 */

public class ExemploP11_02 extends Agent {
    
    @Override
    public void setup() {
        addBehaviour(new CyclicBehaviour() {
            private int execCount;
            
            @Override
            public void action() {
                System.out.println("(" + getAID().getName() + ") - Número de vezes que executei " + ++execCount);
                if (execCount == 100) {
                    removeBehaviour(this);
                }
            }
        });
    }
}
