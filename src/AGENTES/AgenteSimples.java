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

public class AgenteSimples extends Agent {
    
    @Override
    protected void setup() {
        System.out.println("O Agente " + getAID().getName() + " está pronto!");
        
        Object[] args = getArguments();
        if (args != null) {
            System.out.println("Argumentos: ");
            for (int i = 0; i < args.length; ++i) {
                System.out.println("Argumento: " + args[i] + " " + args[i].getClass().getName());
            }
        }
    }
}
