package com.exemple.supercopoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemple.supercopoapi.model.Producao;
import com.exemple.supercopoapi.repository.producao.ProducaoRepositoryQuery;

@Repository
public interface ProducaoRepository extends JpaRepository<Producao, Long>, ProducaoRepositoryQuery{

}
