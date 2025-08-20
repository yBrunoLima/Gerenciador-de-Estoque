/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import io.github.GerenciadorDeEstoque.jdbc.ConexaoRelatorios;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author thall
 */
public class relEstoque {
    ConexaoRelatorios conec = new ConexaoRelatorios();
    public relEstoque() {
        try {
            conec.conecta();
            conec.executaSql("select m.id_mercadoria as ID, m.Descricao, m.Preco, m.QtdeEstoque as Qtd, m.Tamanho, m.Modelo, f.nome as Fornecedor from Mercadoria as m inner join Fornecedor as f on (m.id_fornecedor = f.id_fornecedor)");
            JRResultSetDataSource jr = new JRResultSetDataSource(conec.resultSet);
            JasperPrint jp = JasperFillManager.fillReport("C:\\Users\\thall\\OneDrive\\Documentos\\Gerenciador-de-Estoque\\Gerenciador-de-Estoque\\src\\relatorios\\relEstoque.jasper", new HashMap(),jr);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro ao gerar relatorio");
        }
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
