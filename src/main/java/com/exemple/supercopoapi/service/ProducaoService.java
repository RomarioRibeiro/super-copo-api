package com.exemple.supercopoapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.exemple.supercopoapi.model.Pessoa;
import com.exemple.supercopoapi.model.Producao;
import com.exemple.supercopoapi.repository.PessoaRepository;
import com.exemple.supercopoapi.repository.ProducaoRepository;
import com.exemple.supercopoapi.service.exception.PessoaInexistenteOuInativa;

@Service
public class ProducaoService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	@Autowired
	private ProducaoRepository producaoRepository;
	
	public Producao salvar(Producao producao) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(producao.getPessoa().getCodigo());
		
		if(pessoa.isEmpty() || pessoa.get().isInativo()) {
			throw new PessoaInexistenteOuInativa();
		}
		
		return producaoRepository.save(producao);
	}
	
	private Producao buscarProducaoPeloCodigo(Long codigo) {
		Producao producaoSalvar = producaoRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return producaoSalvar;
	}


	public Producao atualizar(Long codigo, Producao producao) {
		Producao producaoSalvar = buscarProducaoPeloCodigo(codigo);

		BeanUtils.copyProperties(producao, producaoSalvar, "codigo");
		return producaoRepository.save(producaoSalvar);
		
	}
}
