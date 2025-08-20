/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.GerenciadorDeEstoque.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author thall
 */
public class ConexaoRelatorios {
    final private String driver = "com.mysql.jdbc.Driver";
    final private String url = "jdbc:mysql://localhost/GerenciadorDeEstoque";
    final private String usuario = "root";
    final private String senha = "";
    private Connection conexao;
    public ResultSet resultSet;
    public Statement statement;
    
    public boolean conecta(){
        boolean resultado = true;
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,usuario,senha);
        } catch (ClassNotFoundException c) {
            JOptionPane.showMessageDialog(null, "Driver não encontrado");
            resultado = false;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro na fonte de dados");
            resultado = false;
        }
        return resultado;
    }
    
    public void desconecta(){
        try {
            conexao.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao desconectar");
        }
    }
    
     public void executaSql(String sql){
        try {
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao executar");
        }
    }
}
