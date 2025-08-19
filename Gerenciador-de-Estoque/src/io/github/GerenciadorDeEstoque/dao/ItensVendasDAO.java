/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.GerenciadorDeEstoque.dao;

import io.github.GerenciadorDeEstoque.jdbc.Conexao;
import io.github.GerenciadorDeEstoque.model.ItensVendas;
import io.github.GerenciadorDeEstoque.model.Mercadorias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tkmaia
 */
public class ItensVendasDAO {
    private Connection conn;
    
    public ItensVendasDAO(){
        this.conn = new Conexao().pegarConexao();
    }
    
    public void Salvar(ItensVendas obj){
        try {
            String sql = "insert into ItemVenda (quantidade, id_venda, id_mercadoria, subtotal) values (?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,obj.getQnt());
            stmt.setInt(2,obj.getVendas().getId());
            stmt.setInt(3,obj.getMercadorias().getId());
            stmt.setDouble(4,obj.getSubtotal());
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar itens da venda");
        }
    }
    
    public List<ItensVendas>listaItens(int id){
        try {
        List<ItensVendas>lista = new ArrayList();
        String sql = "select m.id_mercadoria, m.descricao, i.quantidade, m.preco, i.subtotal from ItemVenda as i inner join Mercadoria as m on (i.id_mercadoria = m.id_mercadoria) where i.id_venda = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            ItensVendas item = new ItensVendas();
            Mercadorias m = new Mercadorias();
            m.setId(rs.getInt("id_mercadoria"));
            m.setDescricao(rs.getString("descricao"));
            item.setQnt(rs.getInt("quantidade"));
            m.setPreco(rs.getDouble("preco"));
            item.setSubtotal(rs.getDouble("subtotal"));
            item.setMercadorias(m);
            lista.add(item);
        }
        return lista;    
        } catch (SQLException e) {
           throw new RuntimeException("Erro ao criar lista de itens"); 
        }
    }
    
}
