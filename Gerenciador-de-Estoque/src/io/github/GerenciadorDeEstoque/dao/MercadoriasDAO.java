/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.GerenciadorDeEstoque.dao;
import io.github.GerenciadorDeEstoque.jdbc.Conexao;
import io.github.GerenciadorDeEstoque.model.Fornecedores;
import io.github.GerenciadorDeEstoque.model.Mercadorias;
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
public class MercadoriasDAO {
    private Connection conn;
    
    public MercadoriasDAO(){
        this.conn = new Conexao().pegarConexao();
    }
    
    public void Salvar(Mercadorias obj){
        try {
            String sql = "insert into Mercadoria (Descricao, Preco, QtdeEstoque, Tamanho, Modelo, ID_Fornecedor) values(?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQntdEstoque());
            stmt.setString(4, obj.getTamanho());
            stmt.setString(5, obj.getModelo());
            stmt.setInt(6, obj.getFornecedor().getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Mercadoria salva");
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Erro ao tentar salvar Mercadorias");
        }
    }
    
    public Mercadorias Buscar(String nome){
        try {
            String sql = "select m.id_mercadoria, m.Descricao, m.Preco, m.QtdeEstoque, m.Tamanho, m.Modelo, f.nome as nome from Mercadoria as m inner join Fornecedor as f on (m.id_fornecedor = f.id_fornecedor) where m.descricao = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Mercadorias obj = new Mercadorias();
            Fornecedores f = new Fornecedores();
            if (rs.next()) {
                obj.setId(rs.getInt("id_mercadoria"));
                obj.setDescricao(rs.getString("Descricao"));
                obj.setPreco(rs.getDouble("Preco"));
                obj.setQntdEstoque(rs.getInt("QtdeEstoque")); 
                obj.setTamanho(rs.getString("Tamanho"));
                obj.setModelo(rs.getString("Modelo"));
                f.setNome(rs.getString("nome"));
                obj.setFornecedor(f);
                
            }
            return obj;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar buscar mercadoria");
        }
        return null;
    }
    
    public List<Mercadorias>Listar(){
        List<Mercadorias> lista = new ArrayList<>();
        try {
            String sql = "select m.id_mercadoria, m.Descricao, m.Preco, m.QtdeEstoque, m.Tamanho, m.Modelo, f.nome as nome from Mercadoria as m inner join Fornecedor as f on (m.id_fornecedor = f.id_fornecedor)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Mercadorias obj = new Mercadorias();
                Fornecedores f = new Fornecedores();
                obj.setId(rs.getInt("id_mercadoria"));
                obj.setDescricao(rs.getString("Descricao"));
                obj.setPreco(rs.getDouble("Preco"));
                obj.setQntdEstoque(rs.getInt("QtdeEstoque")); 
                obj.setTamanho(rs.getString("Tamanho"));
                obj.setModelo(rs.getString("Modelo"));
                f.setNome(rs.getString("nome"));
                obj.setFornecedor(f);
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar lista de mercadorias");
        }
        return null;
    }
    
    public List<Mercadorias>Filtrar(String nome){
        List<Mercadorias> lista = new ArrayList<>();
        try {
            String sql = "select m.id_mercadoria, m.Descricao, m.Preco, m.QtdeEstoque, m.Tamanho, m.Modelo, f.nome as nome from Mercadoria as m inner join Fornecedor as f on (m.id_fornecedor = f.id_fornecedor) where m.descricao like ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Mercadorias obj = new Mercadorias();
                Fornecedores f = new Fornecedores();
                obj.setId(rs.getInt("id_mercadoria"));
                obj.setDescricao(rs.getString("Descricao"));
                obj.setPreco(rs.getDouble("Preco"));
                obj.setQntdEstoque(rs.getInt("QtdeEstoque")); 
                obj.setTamanho(rs.getString("Tamanho"));
                obj.setModelo(rs.getString("Modelo"));
                f.setNome(rs.getString("nome"));
                obj.setFornecedor(f);
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar lista de mercadorias");
        }
        return null;
    }
    
    public void Editar(Mercadorias obj){
        try {
            String sql = "update Mercadoria set Descricao = ?, Preco = ?, QtdeEstoque = ?, Tamanho = ?, Modelo = ?, id_Fornecedor = ? where id_mercadoria = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQntdEstoque());
            stmt.setString(4, obj.getTamanho());
            stmt.setString(5, obj.getModelo());
            stmt.setInt(6, obj.getFornecedor().getId());
            stmt.setInt(7,obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Mercadoria salva");
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Erro ao tentar editar mercadoria");
        }
    }
    
    public void Excluir(Mercadorias obj){
        try {
            String sql = "delete from Mercadoria where id_mercadoria = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Mercadoria excluido");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar excluir mercadoria");
        }
    }
    
    public List<Mercadorias>FiltrarCOD(int cod){
        List<Mercadorias> lista = new ArrayList<>();
        try {
            String sql = "select m.id_mercadoria, m.Descricao, m.Preco, m.QtdeEstoque, m.Tamanho, m.Modelo, f.nome as nome from Mercadoria as m inner join Fornecedor as f on (m.id_fornecedor = f.id_fornecedor) where m.id_mercadoria = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cod);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Mercadorias obj = new Mercadorias();
                Fornecedores f = new Fornecedores();
                obj.setId(rs.getInt("id_mercadoria"));
                obj.setDescricao(rs.getString("Descricao"));
                obj.setPreco(rs.getDouble("Preco"));
                obj.setQntdEstoque(rs.getInt("QtdeEstoque")); 
                obj.setTamanho(rs.getString("Tamanho"));
                obj.setModelo(rs.getString("Modelo"));
                f.setNome(rs.getString("nome"));
                obj.setFornecedor(f);
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar lista de mercadorias");
        }
        return null;
    }
    
    public int EstoqueAtual(int id){
        try {
            int qtdAtual=0;
            String sql = "select QtdeEstoque from Mercadoria where id_mercadoria = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                qtdAtual = (rs.getInt("QtdeEstoque"));
            }
            return qtdAtual;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao retornar estoque");
        }
    }
    
    public void AdicionarEstoque(int id, int qntd){
        try {
            String sql = "update Mercadoria set QtdeEstoque = QtdeEstoque + ? where id_mercadoria = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, qntd);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Adicionado com sucesso");
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao adicionar");
        }
    }
    
    public void DiminuirEstoque(int id, int qntd){
        try {
            String sql = "update Mercadoria set QtdeEstoque = QtdeEstoque - ? where id_mercadoria = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, qntd);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao diminuir");
        }
    }
    
}
