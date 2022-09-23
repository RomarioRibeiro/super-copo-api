package com.exemple.supercopoapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemple.supercopoapi.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	List<Pessoa> findByNome(String nome);
	List<Pessoa> findByNomeContaining(String nome);
}
