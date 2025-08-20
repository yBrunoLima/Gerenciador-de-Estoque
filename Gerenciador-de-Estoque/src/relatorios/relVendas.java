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
public class relVendas {
    ConexaoRelatorios conec = new ConexaoRelatorios();
    public relVendas() {
        try {
            conec.conecta();
            conec.executaSql("SELECT v.ID_Venda AS ID, DATE_FORMAT(v.DataVenda, '%d/%m/%Y') as Data, c.Nome AS Cliente, CONCAT('R$ ', FORMAT(v.ValorTotal, 2, 'pt_BR')) as Total FROM Venda AS v INNER JOIN Cliente AS c ON (v.ID_Cliente = c.ID_Cliente);");
            JRResultSetDataSource jr = new JRResultSetDataSource(conec.resultSet);
            JasperPrint jp = JasperFillManager.fillReport("relVendas.jasper", new HashMap(),jr);
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
