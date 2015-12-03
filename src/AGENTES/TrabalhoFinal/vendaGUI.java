/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AGENTES.TrabalhoFinal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author lennon
 */
public class vendaGUI extends JFrame {
    private AgenteVendedor myAgent;
    private JTextField nomeProduto, precoProduto;
    
    vendaGUI(AgenteVendedor av) {
        super(av.getLocalName());
        
        myAgent = av;
        
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 2));
        
        p.add(new JLabel("Nome do produto: "));
        nomeProduto = new JTextField(15);
        p.add(nomeProduto);
        
        p.add(new JLabel("Preço do produto: "));
        precoProduto = new JTextField(15);
        p.add(precoProduto);
        
        getContentPane().add(p, BorderLayout.CENTER);
        
        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    String produto = nomeProduto.getText().trim();
                    String preco = precoProduto.getText().trim();
                    myAgent.atualizaCatalogo(produto, Integer.parseInt(preco));
                    
                    nomeProduto.setText("");
                    precoProduto.setText("");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(vendaGUI.this,
                            "Valores inválidos. " + e.getMessage(),
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        p = new JPanel();
        p.add(addButton);
        getContentPane().add(p, BorderLayout.SOUTH);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                myAgent.doDelete();
            }
        });
        
        setResizable(false);
    }
    
    @Override
    public void show() {
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int)screenSize.getWidth() / 2;
        int centerY = (int)screenSize.getHeight() / 2;
        setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
        super.show();
    }
}
