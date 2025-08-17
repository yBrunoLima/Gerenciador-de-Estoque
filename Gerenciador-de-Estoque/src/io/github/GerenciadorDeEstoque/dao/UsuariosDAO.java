/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.GerenciadorDeEstoque.dao;
import io.github.GerenciadorDeEstoque.jdbc.Conexao;
import io.github.GerenciadorDeEstoque.model.Usuarios;
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
public class UsuariosDAO {
    private Connection conn;
    
    public UsuariosDAO(){
        this.conn = new Conexao().pegarConexao();
    }
    
    public void Salvar(Usuarios obj){
        try {
            String sql = "insert into Usuario (nome, login, senha, tipousuario) values(?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getLogin());
            stmt.setString(3,obj.getSenha());
            stmt.setString(4,obj.getTipo());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Usuario salvo");
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Erro ao tentar salvar Usuario");
        }
    }
    
    public Usuarios Buscar(String nome){
        try {
            String sql = "select * from Usuario where nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Usuarios obj = new Usuarios();
            if (rs.next()) {
                obj.setId(rs.getInt("id_usuario"));
                obj.setNome(rs.getString("nome"));
                obj.setLogin(rs.getString("login"));
                obj.setSenha(rs.getString("senha"));
                obj.setTipo(rs.getString("tipousuario"));
            }
            return obj;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar buscar usuario");
        }
        return null;
    }
    
    public List<Usuarios>Listar(){
        List<Usuarios> lista = new ArrayList<>();
        try {
            String sql = "select * from Usuario";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Usuarios obj = new Usuarios();
                obj.setId(rs.getInt("id_usuario"));
                obj.setNome(rs.getString("nome"));
                obj.setLogin(rs.getString("login"));
                obj.setSenha(rs.getString("senha"));
                obj.setTipo(rs.getString("tipousuario"));
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar lista de clientes");
        }
        return null;
    }
    
    public List<Usuarios>Filtrar(String nome){
        List<Usuarios> lista = new ArrayList<>();
        try {
            String sql = "select * from Usuario where nome like ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Usuarios obj = new Usuarios();
                obj.setId(rs.getInt("id_usuario"));
                obj.setNome(rs.getString("nome"));
                obj.setLogin(rs.getString("login"));
                obj.setSenha(rs.getString("senha"));
                obj.setTipo(rs.getString("tipousuario"));
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar lista de usuarios");
        }
        return null;
    }
    
    public void Editar(Usuarios obj){
        try {
            String sql = "update Usuario set nome = ?, login = ?, senha = ?, tipousuario = ? where id_usuario = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getLogin());
            stmt.setString(3,obj.getSenha());
            stmt.setString(4,obj.getTipo());
            stmt.setInt(5,obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Usuario salvo");
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Erro ao tentar editar usuario");
        }
    }
    
    public void Excluir(Usuarios obj){
        try {
            String sql = "delete from Usuario where id_usuario = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Usuario excluido");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar excluir usuario");
        }
    }
    
}
