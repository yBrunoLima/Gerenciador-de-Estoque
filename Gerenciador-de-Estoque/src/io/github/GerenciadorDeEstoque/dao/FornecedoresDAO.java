/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.GerenciadorDeEstoque.dao;
import io.github.GerenciadorDeEstoque.jdbc.Conexao;
import io.github.GerenciadorDeEstoque.model.Fornecedores;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author tkmaia
 */
public class FornecedoresDAO {
    private Connection conn;
    
    public FornecedoresDAO(){
        this.conn = new Conexao().pegarConexao();
    }
    
    public void Salvar(Fornecedores obj){
        try {
            String sql = "insert into Fornecedor (nome, endereco, cnpj, cpf) values(?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getEndereco());
            stmt.setString(3,obj.getCnpj());
            stmt.setString(4,obj.getCpf());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Fornecedor salvo");
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Erro ao tentar salvar Fornecedor");
        }
    }
    
    public Fornecedores Buscar(String nome){
        try {
            String sql = "select * from Fornecedor where nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Fornecedores obj = new Fornecedores();
            if (rs.next()) {
                obj.setId(rs.getInt("id_fornecedor"));
                obj.setNome(rs.getString("nome"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setCpf(rs.getString("cpf"));
            }
            return obj;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar buscar Fornecedor");
        }
        return null;
    }
    
    public List<Fornecedores>Listar(){
        List<Fornecedores> lista = new ArrayList<>();
        try {
            String sql = "select * from Fornecedor";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Fornecedores obj = new Fornecedores();
                obj.setId(rs.getInt("id_fornecedor"));
                obj.setNome(rs.getString("nome"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setCpf(rs.getString("cpf"));
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar lista de fornecedores");
        }
        return null;
    }
    
    public List<Fornecedores>Filtrar(String nome){
        List<Fornecedores> lista = new ArrayList<>();
        try {
            String sql = "select * from Fornecedor where nome like ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Fornecedores obj = new Fornecedores();
                obj.setId(rs.getInt("id_fornecedor"));
                obj.setNome(rs.getString("nome"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setCpf(rs.getString("cpf"));
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar lista de fornecedores");
        }
        return null;
    }
    
    public void Editar(Fornecedores obj){
        try {
            String sql = "update Fornecedor set nome = ?, endereco = ?, cnpj = ?, cpf = ? where id_fornecedor = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getEndereco());
            stmt.setString(3,obj.getCnpj());
            stmt.setString(4,obj.getCpf());
            stmt.setInt(5,obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Fornecedor salvo");
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Erro ao tentar editar fornecedor");
        }
    }
    
    public void Excluir(Fornecedores obj){
        try {
            String sql = "delete from Fornecedor where id_fornecedor = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Fornecedor excluido");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar excluir fornecedor");
        }
    }
    
}
