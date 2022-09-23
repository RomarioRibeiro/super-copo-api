package com.exemple.supercopoapi.repository.filter;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProducaoFilter {

	private String descricao;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataProducaoDe;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataProducaoAte;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataProducaoDe() {
		return dataProducaoDe;
	}

	public void setDataProducaoDe(LocalDate dataProducaoDe) {
		this.dataProducaoDe = dataProducaoDe;
	}

	public LocalDate getDataProducaoAte() {
		return dataProducaoAte;
	}

	public void setDataProducaoAte(LocalDate dataProducaoAte) {
		this.dataProducaoAte = dataProducaoAte;
	}

}
