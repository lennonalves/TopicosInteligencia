/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AGENTES;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

/**
 *
 * @author lennon
 */

public class ExemploP11_01 extends Agent {
    
    @Override
    public void setup() {
        addBehaviour(new OneShotBehaviour() {
            
            @Override
            public void action() {
                System.out.println("(" + getAID().getName() + ") - Executei uma única vez");
            }
        });
    }
}
