package br.com.caelum.relatorio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.relatorio.modelo.Categoria;
import br.com.caelum.relatorio.modelo.Conta;
import br.com.caelum.relatorio.modelo.Movimentacao;
import br.com.caelum.relatorio.modelo.TipoMovimentacao;

public class MovimentacaoDAO {
	private Connection connection;

	public MovimentacaoDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Movimentacao> todos() {
		try {
			String sql = 
				"SELECT "+
						"movimentacoes.id AS id, "+
						"movimentacoes.data AS data, "+
						"movimentacoes.descricao AS descricao, "+
						"movimentacoes.tipoMovimentacao AS tipo, "+
						"movimentacoes.valor AS valor, "+
						"categorias.id AS categoriaId, "+
						"categorias.nome AS categoriaNome, "+
						"contas.id AS contaId, "+
						"contas.titular AS titular, "+
						"contas.numero AS numero, "+
						"contas.agencia AS agencia, "+
						"contas.banco AS banco "+
				" FROM "+
						"movimentacoes "+
						"INNER JOIN categorias on movimentacoes.categoria_id = categorias.id "+
						"INNER JOIN contas on movimentacoes.conta_id = contas.id";
			
			PreparedStatement statement = this.connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			List<Movimentacao> lista = new ArrayList<Movimentacao>();
			
			while(rs.next()) {
				Movimentacao movimentacao = new Movimentacao();
				movimentacao.setId(rs.getInt(1));
				Calendar cal = Calendar.getInstance();
				cal.setTime(rs.getDate(2));
				movimentacao.setData(cal);
				movimentacao.setDescricao(rs.getString(3));
				TipoMovimentacao tipo = TipoMovimentacao.valueOf(rs.getString(4));
				movimentacao.setTipoMovimentacao(tipo);
				movimentacao.setValor(rs.getBigDecimal(5));
				
				Categoria cat = new Categoria();
				cat.setId(rs.getInt(6));
				cat.setNome(rs.getString(7));
				
				movimentacao.setCategoria(cat);
				
				Conta conta = new Conta();
				conta.setId(rs.getInt(8));
				conta.setTitular(rs.getString(9));
				conta.setNumero(rs.getString(10));
				conta.setAgencia(rs.getString(11));
				conta.setBanco(rs.getString(12));
				
				movimentacao.setConta(conta);
				
				lista.add(movimentacao);
			}
			
			return lista;
					
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
