package com.exemple.supercopoapi.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ProducaoFilter {

	private String descricao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataProducaoDe;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
