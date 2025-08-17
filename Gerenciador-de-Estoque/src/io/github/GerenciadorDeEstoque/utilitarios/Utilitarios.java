/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.GerenciadorDeEstoque.utilitarios;

import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JPanel;

/**
 *
 * @author tkmaia
 */
public class Utilitarios {
    public void LimparTela(JPanel container){
        Component components[] = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField)component).setText(null);
            }
        }    
    }
}
