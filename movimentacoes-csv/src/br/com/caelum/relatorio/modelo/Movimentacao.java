package br.com.caelum.relatorio.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

public class Movimentacao {

	private Integer id;
	private String descricao;
	private Calendar data;
	private BigDecimal valor;
	private TipoMovimentacao tipoMovimentacao;
	private Categoria categoria;
	private Conta conta;
	
	public Movimentacao() {
	}

	public Movimentacao(Calendar data, String descricao, 
			TipoMovimentacao tipoMovimentacao, BigDecimal valor) {
		this.data = (Calendar) data.clone();
		this.tipoMovimentacao = tipoMovimentacao;
		this.valor = valor;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public Calendar getData() {
		return data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Override
	public String toString() {
		return "Movimentacao [id=" + id + ", descricao=" + descricao
				+ ", valor=" + valor + ", tipoMovimentacao=" + tipoMovimentacao +
				"]";
	}

}
