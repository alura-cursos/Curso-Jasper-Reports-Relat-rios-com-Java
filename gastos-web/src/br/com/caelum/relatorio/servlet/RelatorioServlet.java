package br.com.caelum.relatorio.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.relatorio.ConnectionFactory;
import br.com.caelum.relatorio.gerador.GeradorRelatorio;

@WebServlet("/movimentacoes")
public class RelatorioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String nome = request.getServletContext().getRealPath("/WEB-INF/jasper/gastos_por_mes.jasper");
			Connection connection = new ConnectionFactory().getConnection();
			
			String dataIni = request.getParameter("data_ini");
			String dataFim = request.getParameter("data_fim");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			Date dataInicial = sdf.parse(dataIni);
			Date dataFinal = sdf.parse(dataFim);

			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("DATA_INI", dataInicial);
			parametros.put("DATA_FIM", dataFinal);
			
			GeradorRelatorio gerador = new GeradorRelatorio(nome, parametros, connection);
			
			gerador.geraPDFParaOutputStream(response.getOutputStream());
			
			connection.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (ParseException e) {
			throw new ServletException(e);
		} 
	}
}
