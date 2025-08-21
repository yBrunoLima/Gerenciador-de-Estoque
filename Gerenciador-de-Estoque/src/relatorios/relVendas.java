/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author thall
 */
public class relVendas {

    public relVendas() {
        Connection conexao = null;

        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost/GerenciadorDeEstoque";
            String usuario = "root";
            String senha = "";
            
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);

            String caminhoJasper = "relatorios/relVendas.jasper";
            InputStream relatorioStream = getClass().getClassLoader().getResourceAsStream(caminhoJasper);

            if (relatorioStream == null) {
                throw new JRException("Arquivo .jasper não encontrado no caminho: " + caminhoJasper);
            }

            JasperPrint jp = JasperFillManager.fillReport(relatorioStream, new HashMap<>(), conexao);

            JasperViewer.viewReport(jp, false);

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro: Driver do banco de dados não encontrado.\nVerifique se o JAR do MySQL Connector/J está nas bibliotecas.");
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados:\n" + e.getMessage());
            e.printStackTrace();
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório:\n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado:\n" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    
                }
            }
        }
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
