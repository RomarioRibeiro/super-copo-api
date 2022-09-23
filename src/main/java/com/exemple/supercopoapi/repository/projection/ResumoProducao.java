package com.exemple.supercopoapi.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ResumoProducao {

	private Long codigo;
	private String descricao;
	private LocalDate dataProducao;
	private LocalDate dataFinalizacao;
	private BigDecimal quantidade;
	private String observacao;
	private String maquina;
	private String pessoa;

	public ResumoProducao(Long codigo, String descricao, LocalDate dataProducao, LocalDate dataFinalizacao,
			BigDecimal quantidade, String observacao, String maquina, String pessoa) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.dataProducao = dataProducao;
		this.dataFinalizacao = dataFinalizacao;
		this.quantidade = quantidade;
		this.observacao = observacao;
		this.maquina = maquina;
		this.pessoa = pessoa;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataProducao() {
		return dataProducao;
	}

	public void setDataProducao(LocalDate dataProducao) {
		this.dataProducao = dataProducao;
	}

	public LocalDate getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(LocalDate dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getMaquina() {
		return maquina;
	}

	public void setMaquina(String maquina) {
		this.maquina = maquina;
	}

	public String getPessoa() {
		return pessoa;
	}

	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}
	

}
