/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package io.github.GerenciadorDeEstoque.jdbc;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author tkmaia
 */
public class TestarConexao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            new Conexao().pegarConexao();
            JOptionPane.showMessageDialog(null, "Conectado com sucesso ao banco de dados");
        } catch (HeadlessException erro){
            JOptionPane.showMessageDialog(null, "Erro ao tentar conectar ao banco de dados" + erro.getMessage());
        }
    }
    
}
