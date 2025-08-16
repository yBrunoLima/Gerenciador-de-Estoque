/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.GerenciadorDeEstoque.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author tkmaia
 */
public class Conexao {
    final private String url = "jdbc:mysql://localhost/GerenciadorDeEstoque";
    final private String usuario = "root";
    final private String senha = "";
    
    public Connection pegarConexao(){
        try {
            return DriverManager.getConnection(url,usuario,senha);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de conexao com o banco de dados");
        }
        return null;
        
    }
}
