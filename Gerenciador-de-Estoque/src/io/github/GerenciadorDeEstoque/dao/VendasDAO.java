/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.GerenciadorDeEstoque.dao;


import io.github.GerenciadorDeEstoque.jdbc.Conexao;
import io.github.GerenciadorDeEstoque.model.Clientes;
import io.github.GerenciadorDeEstoque.model.Vendas;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author tkmaia
 */
public class VendasDAO {
    private Connection conn;
    
    public VendasDAO(){
        this.conn = new Conexao().pegarConexao();
    }
    
    public void Salvar(Vendas obj){
        try {
            String sql = "insert into Venda (datavenda, valortotal, id_cliente) values (?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getData());
            stmt.setDouble(2,obj.getTotal());
            stmt.setInt(3,obj.getCliente().getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Venda confirmada");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao realizar venda");
        }
    }
    
    public int RetornaIdVenda(){
        try {
            int ultimoId = 0;
            String sql = "select max(id_venda) id_venda from Venda";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Vendas v = new Vendas();
                v.setId(rs.getInt("id_venda"));
                ultimoId = v.getId();
            }
            return ultimoId;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao retornar ID da venda");
        }
    }
    
    public List<Vendas>Historico(LocalDate dataInicio,LocalDate dataFim){
        try {
            List<Vendas>lista = new ArrayList<>();
            String sql = "select v.id_venda, date_format(v.datavenda, '%d/%m/%Y') as dataBr ,v.valortotal, c.nome from Venda as v inner join Cliente as c on (v.id_cliente = c.id_cliente) where datavenda between ? and ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,dataInicio.toString());
            stmt.setString(2,dataFim.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Vendas v = new Vendas();
                Clientes c = new Clientes();
                v.setId(rs.getInt("id_venda"));
                v.setData(rs.getString("dataBr"));
                v.setTotal(rs.getDouble("valortotal"));
                c.setNome(rs.getString("nome"));
                v.setCliente(c);
                lista.add(v);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao retornar ID da venda");
        }
    }
    
}
