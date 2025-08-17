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
            String sql = "insert into Mercadoria (Descricao, PrecoCompra, PrecoVenda, QtdeEstoque, EstoqueMinimo, EstoqueMaximo, Unidade, Tamanho, Modelo, ID_Fornecedor) values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPrecoCompra());
            stmt.setDouble(3, obj.getPrecoVenda());
            stmt.setInt(4, obj.getQntdEstoque());
            stmt.setInt(5, obj.getEstoqueMin());
            stmt.setInt(6, obj.getEstoqueMax());
            stmt.setString(7, obj.getUnidade());
            stmt.setString(8, obj.getTamanho());
            stmt.setString(9, obj.getModelo());
            stmt.setInt(10, obj.getFornecedor().getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Mercadoria salva");
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Erro ao tentar salvar Mercadorias");
        }
    }
    
    public Mercadorias Buscar(String nome){
        try {
            String sql = "select m.id_mercadoria, m.Descricao, m.PrecoCompra, m.PrecoVenda, m.QtdeEstoque, m.EstoqueMinimo, m.EstoqueMaximo, m.Unidade, m.Tamanho, m.Modelo, f.nome as nome from Mercadoria as m inner join Fornecedor as f on (m.id_fornecedor = f.id_fornecedor) where m.descricao = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Mercadorias obj = new Mercadorias();
            Fornecedores f = new Fornecedores();
            if (rs.next()) {
                obj.setId(rs.getInt("id_mercadoria"));
                obj.setDescricao(rs.getString("Descricao"));
                obj.setPrecoCompra(rs.getDouble("PrecoCompra"));
                obj.setPrecoVenda(rs.getDouble("PrecoVenda"));
                obj.setQntdEstoque(rs.getInt("QtdeEstoque")); 
                obj.setEstoqueMin(rs.getInt("EstoqueMinimo"));
                obj.setEstoqueMax(rs.getInt("EstoqueMaximo"));
                obj.setUnidade(rs.getString("Unidade"));
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
            String sql = "select m.id_mercadoria, m.Descricao, m.PrecoCompra, m.PrecoVenda, m.QtdeEstoque, m.EstoqueMinimo, m.EstoqueMaximo, m.Unidade, m.Tamanho, m.Modelo, f.nome as nome from Mercadoria as m inner join Fornecedor as f on (m.id_fornecedor = f.id_fornecedor)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Mercadorias obj = new Mercadorias();
                Fornecedores f = new Fornecedores();
                obj.setId(rs.getInt("id_mercadoria"));
                obj.setDescricao(rs.getString("Descricao"));
                obj.setPrecoCompra(rs.getDouble("PrecoCompra"));
                obj.setPrecoVenda(rs.getDouble("PrecoVenda"));
                obj.setQntdEstoque(rs.getInt("QtdeEstoque")); 
                obj.setEstoqueMin(rs.getInt("EstoqueMinimo"));
                obj.setEstoqueMax(rs.getInt("EstoqueMaximo"));
                obj.setUnidade(rs.getString("Unidade"));
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
            String sql = "select m.id_mercadoria, m.Descricao, m.PrecoCompra, m.PrecoVenda, m.QtdeEstoque, m.EstoqueMinimo, m.EstoqueMaximo, m.Unidade, m.Tamanho, m.Modelo, f.nome as nome from Mercadoria as m inner join Fornecedor as f on (m.id_fornecedor = f.id_fornecedor) where m.descricao like ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Mercadorias obj = new Mercadorias();
                Fornecedores f = new Fornecedores();
                obj.setId(rs.getInt("id_mercadoria"));
                obj.setDescricao(rs.getString("Descricao"));
                obj.setPrecoCompra(rs.getDouble("PrecoCompra"));
                obj.setPrecoVenda(rs.getDouble("PrecoVenda"));
                obj.setQntdEstoque(rs.getInt("QtdeEstoque")); 
                obj.setEstoqueMin(rs.getInt("EstoqueMinimo"));
                obj.setEstoqueMax(rs.getInt("EstoqueMaximo"));
                obj.setUnidade(rs.getString("Unidade"));
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
            String sql = "update Mercadoria set Descricao = ?, PrecoCompra = ?, PrecoVenda = ?, QtdeEstoque = ?, EstoqueMinimo = ?, EstoqueMaximo = ?, Unidade = ?, Tamanho = ?, Modelo = ?, id_Fornecedor = ? where id_mercadoria = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPrecoCompra());
            stmt.setDouble(3, obj.getPrecoVenda());
            stmt.setInt(4, obj.getQntdEstoque());
            stmt.setInt(5, obj.getEstoqueMin());
            stmt.setInt(6, obj.getEstoqueMax());
            stmt.setString(7, obj.getUnidade());
            stmt.setString(8, obj.getTamanho());
            stmt.setString(9, obj.getModelo());
            stmt.setInt(10, obj.getFornecedor().getId());
            stmt.setInt(11,obj.getId());
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
    
}
