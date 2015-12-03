/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AGENTES.TrabalhoFinal;

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
    public static void main (String[] args) {
        Runtime r = Runtime.instance();
        Profile p = new ProfileImpl();
        ContainerController cc = r.createMainContainer(p);
        AgentController rma, ac;
        try {
            rma = cc.createNewAgent("rma", "jade.tools.rma.rma", null);
            rma.start();
            ac = cc.createNewAgent("GeorgeHarrison", "AGENTES.TrabalhoFinal.AgenteVendedor", null);
            ac.start();
            ac = cc.createNewAgent("RingoStarr", "AGENTES.TrabalhoFinal.AgenteVendedor", null);
            ac.start();
            ac = cc.createNewAgent("JohnLennon", "AGENTES.TrabalhoFinal.AgenteComprador", new String[]{"Guitarra"});
            ac.start();
            ac = cc.createNewAgent("PaulMcCartney", "AGENTES.TrabalhoFinal.AgenteComprador", new String[]{"Guitarra"});
            ac.start();
        } catch (StaleProxyException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
