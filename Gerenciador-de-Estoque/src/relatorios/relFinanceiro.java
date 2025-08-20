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
public class relFinanceiro {
    ConexaoRelatorios conec = new ConexaoRelatorios();
    public relFinanceiro() {
        try {
            conec.conecta();
            conec.executaSql("SELECT CONCAT('R$ ', FORMAT(SUM(IF(DataVenda = CURDATE(), ValorTotal, 0)), 2, 'pt_BR')) AS TotalHoje, CONCAT('R$ ', FORMAT(SUM(IF(YEARWEEK(DataVenda, 0) = YEARWEEK(CURDATE(), 0), ValorTotal, 0)), 2, 'pt_BR')) AS TotalSemana, CONCAT('R$ ', FORMAT(SUM(IF(YEAR(DataVenda) = YEAR(CURDATE()) AND MONTH(DataVenda) = MONTH(CURDATE()), ValorTotal, 0)), 2, 'pt_BR')) AS TotalMes FROM Venda;");
            JRResultSetDataSource jr = new JRResultSetDataSource(conec.resultSet);
            JasperPrint jp = JasperFillManager.fillReport("C:\\Users\\thall\\OneDrive\\Documentos\\Gerenciador-de-Estoque\\Gerenciador-de-Estoque\\src\\relatorios\\relFinanceiro.jasper", new HashMap(),jr);
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
