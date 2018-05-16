package br.com.caelum.financas.relatorio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import br.com.caelum.financas.ConnectionFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class TesteGeraRelatorio {

	public static void main(String[] args) throws SQLException, JRException, FileNotFoundException {

//		JasperCompileManager.compileReportToFile("gastos_por_mes_subreport1.jrxml"); 
		
		Connection connection = new ConnectionFactory().getConnection();
		
		Map<String, Object> params = new HashMap<String, Object>();
		JasperPrint jasperPrint = JasperFillManager.fillReport("gastos_por_mes.jasper", params,connection);
		
		JRExporter exporter = new JRPdfExporter();
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("gasto_por_mes.pdf"));
		
		exporter.exportReport();
		
		connection.close();
	}
}
