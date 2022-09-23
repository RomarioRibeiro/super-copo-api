package com.exemple.supercopoapi.repository.producao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.exemple.supercopoapi.model.Producao;
import com.exemple.supercopoapi.repository.filter.ProducaoFilter;
import com.exemple.supercopoapi.repository.projection.ResumoProducao;

public interface ProducaoRepositoryQuery {

	public Page<Producao> filtrar(ProducaoFilter producaoFilter, Pageable pageable);
	public Page<ResumoProducao> resumir(ProducaoFilter producaoFilter, Pageable pageable);
	
}
