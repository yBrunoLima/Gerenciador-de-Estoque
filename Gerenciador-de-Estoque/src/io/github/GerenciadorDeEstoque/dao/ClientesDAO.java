/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.GerenciadorDeEstoque.dao;
import io.github.GerenciadorDeEstoque.jdbc.Conexao;
import io.github.GerenciadorDeEstoque.model.Clientes;
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
public class ClientesDAO {
    private Connection conn;
    
    public ClientesDAO(){
        this.conn = new Conexao().pegarConexao();
    }
    
    public void Salvar(Clientes obj){
        try {
            String sql = "insert into Cliente (nome, telefone, email, cpf, endereco) values(?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getTelefone());
            stmt.setString(3,obj.getEmail());
            stmt.setString(4,obj.getCpf());
            stmt.setString(5,obj.getEndereco());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente salvo");
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Erro ao tentar salvar cliente");
        }
    }
    
    public Clientes Buscar(String nome){
        try {
            String sql = "select * from Cliente where nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Clientes obj = new Clientes();
            if (rs.next()) {
                obj.setId(rs.getInt("id_cliente"));
                obj.setNome(rs.getString("nome"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setEmail(rs.getString("email"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEndereco(rs.getString("endereco"));
            }
            return obj;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar buscar cliente");
        }
        return null;
    }
    
    public Clientes BuscarCpf(String cpf){
        try {
            String sql = "select * from Cliente where cpf = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            Clientes obj = new Clientes();
            if (rs.next()) {
                obj.setId(rs.getInt("id_cliente"));
                obj.setNome(rs.getString("nome"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setEmail(rs.getString("email"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEndereco(rs.getString("endereco"));
            }
            return obj;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar buscar cliente");
        }
        return null;
    }
    
    public List<Clientes>Listar(){
        List<Clientes> lista = new ArrayList<>();
        try {
            String sql = "select * from Cliente";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Clientes obj = new Clientes();
                obj.setId(rs.getInt("id_cliente"));
                obj.setNome(rs.getString("nome"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setEmail(rs.getString("email"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEndereco(rs.getString("endereco"));
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar lista de clientes");
        }
        return null;
    }
    
    public List<Clientes>Filtrar(String nome){
        List<Clientes> lista = new ArrayList<>();
        try {
            String sql = "select * from Cliente where nome like ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Clientes obj = new Clientes();
                obj.setId(rs.getInt("id_cliente"));
                obj.setNome(rs.getString("nome"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setEmail(rs.getString("email"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEndereco(rs.getString("endereco"));
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar lista de clientes");
        }
        return null;
    }
    
    public void Editar(Clientes obj){
        try {
            String sql = "update Cliente set nome = ?, telefone = ?, email = ?, cpf = ?, endereco = ? where id_cliente = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getTelefone());
            stmt.setString(3,obj.getEmail());
            stmt.setString(4,obj.getCpf());
            stmt.setString(5,obj.getEndereco());
            stmt.setInt(6,obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente salvo");
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Erro ao tentar editar cliente");
        }
    }
    
    public void Excluir(Clientes obj){
        try {
            String sql = "delete from Cliente where id_cliente = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente excluido");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar excluir cliente");
        }
    }
    
}
