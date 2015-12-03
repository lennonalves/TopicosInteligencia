/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AGENTES;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

/**
 *
 * @author lennon
 */

public class Principal {
    public static void main(String[] args) {
        Runtime r = Runtime.instance();
        Profile p = new ProfileImpl();
        ContainerController cc = r.createMainContainer(p);
        AgentController ac;
        AgentController rma;
        try {
            rma = cc.createNewAgent("rma", "jade.tools.rma.rma", null);
            rma.start();
            
//            ac = cc.createNewAgent("AndreP", "AGENTES.AgenteSimples", null);
//            String[] argumentos = {"a", "b"};
//            ac = cc.createNewAgent("Alessandro", "AGENTES.AgenteSimples", argumentos);
//            ac.start();
//            ac = cc.createNewAgent("Rogerio", "AGENTES.AgenteSimplesComTermino", null);
//            ac.start();
//            ac = cc.createNewAgent("Geraldo", "AGENTES.ExemploP08", null);
//            ac.start();
//            ac = cc.createNewAgent("Richard", "AGENTES.ExemploP11_01", null);
//            ac.start();
//            ac = cc.createNewAgent("Mauren", "AGENTES.ExemploP11_02", null);
//            ac.start();
//            ac = cc.createNewAgent("Sheila", "AGENTES.ExemploP11_03", null);
//            ac.start();
//            ac = cc.createNewAgent("Denis", "AGENTES.ExemploP11_04", null);
//            ac.start();
//            ac = cc.createNewAgent("Luiz", "AGENTES.ExemploP09", null);
//            ac.start();
//            ac = cc.createNewAgent("Tania", "AGENTES.ExemploP11_01", null);
//            ac.start();
//            ac = cc.createNewAgent("Tarcizio", "AGENTES.ExemploP11_02", null);
//            ac.start();
//            ac = cc.createNewAgent("Vinicius", "AGENTES.ExemploP11_03", null);
//            ac.start();
//            ac = cc.createNewAgent("Almeida", "AGENTES.ExemploP11_04", null);
//            ac.start();
//            ac = cc.createNewAgent("Nasser", "AGENTES.ExemploP17_01", null);
//            ac.start();
//            ac = cc.createNewAgent("Sara", "AGENTES.ExemploP17_02", null);
//            ac.start();
//            ac = cc.createNewAgent("Mathias", "AGENTES.ExemploP18_01", null);
//            ac.start();
//            ac = cc.createNewAgent("Erikson", "AGENTES.ExemploP19_01", null);
//            ac.start();
            ac = cc.createNewAgent("Monica", "AGENTES.TrocaDeMensagens", new String[]{"AndreK"});
            ac.start();
            ac = cc.createNewAgent("AndreK", "AGENTES.TrocaDeMensagens", new String[]{"Monica"});
            ac.start();
        } catch (StaleProxyException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
