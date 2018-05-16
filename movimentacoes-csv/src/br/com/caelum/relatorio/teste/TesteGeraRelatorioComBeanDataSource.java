package br.com.caelum.relatorio.teste;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.caelum.relatorio.GeradorRelatorio;
import br.com.caelum.relatorio.modelo.Movimentacao;
import br.com.caelum.relatorio.modelo.TipoMovimentacao;

public class TesteGeraRelatorioComBeanDataSource {

	public static void main(String[] args) throws SQLException, JRException, FileNotFoundException {

		JasperCompileManager.compileReportToFile("movimentacoes.jrxml"); 

		Map<String, Object> parametros = new HashMap<String, Object>();

		List<Movimentacao> lista = Arrays.asList(
				new Movimentacao(Calendar.getInstance(),"Telefone",TipoMovimentacao.SAIDA,new BigDecimal("143.7")),
				new Movimentacao(Calendar.getInstance(),"Agua",TipoMovimentacao.SAIDA,new BigDecimal("36.8")),
				new Movimentacao(Calendar.getInstance(),"Luz",TipoMovimentacao.SAIDA,new BigDecimal("46.91"))
				);

		JRBeanCollectionDataSource dataSource = null;
		
//		GeradorRelatorio geradorRelatorio = new GeradorRelatorio("movimentacoes.jasper", parametros, dataSource);
//		geradorRelatorio.geraPDFPara(new FileOutputStream("movimentacoes.pdf"));
	}
}
