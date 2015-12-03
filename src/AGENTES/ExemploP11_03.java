/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AGENTES;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;

/**
 *
 * @author lennon
 */

public class ExemploP11_03 extends Agent {
    
    @Override
    public void setup() {
        SequentialBehaviour seq = new SequentialBehaviour();
        CounterBehaviour[] contadores = new CounterBehaviour[10];
        
        for (int i = 0; i < contadores.length; i++) {
            contadores[i] = new CounterBehaviour(i);
            seq.addSubBehaviour(contadores[i]);
        }
        
        addBehaviour(seq);
    }
    
    private class CounterBehaviour extends OneShotBehaviour {
        private int id;
        
        public CounterBehaviour(int id) {
            this.id = id;
        }
        
        @Override
        public void action() {
            for (int i = 0; i < 5; i++) {
                System.out.println("Behaviour " + id + " - contador = " + i);
            }
        }
    }
}
