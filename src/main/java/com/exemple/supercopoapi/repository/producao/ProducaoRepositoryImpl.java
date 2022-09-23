package com.exemple.supercopoapi.repository.producao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import com.exemple.supercopoapi.model.Maquina_;
import com.exemple.supercopoapi.model.Pessoa_;
import com.exemple.supercopoapi.model.Producao;
import com.exemple.supercopoapi.model.Producao_;
import com.exemple.supercopoapi.repository.filter.ProducaoFilter;
import com.exemple.supercopoapi.repository.projection.ResumoProducao;

public class ProducaoRepositoryImpl implements ProducaoRepositoryQuery{

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<Producao> filtrar(ProducaoFilter producaoFilter, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Producao> criteria = builder.createQuery(Producao.class);
		Root<Producao> root = criteria.from(Producao.class);
		
		
		Predicate[]  predicates = criarRestricoes(producaoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Producao> query  = entityManager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(pageable, query);
		
		return  new PageImpl<>(query.getResultList(), pageable, total(producaoFilter))  ;
	}

	
	@Override
	public Page<ResumoProducao> resumir(ProducaoFilter producaoFilter, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ResumoProducao> criteria = builder.createQuery(ResumoProducao.class);
		Root<Producao> root = criteria.from(Producao.class);
		
		criteria.select(builder.construct(ResumoProducao.class
				, root.get(Producao_.codigo), root.get(Producao_.descricao)
				, root.get(Producao_.dataProducao), root.get(Producao_.dataFinalizacao)
				, root.get(Producao_.quantidade), root.get(Producao_.observacao)
				, root.get(Producao_.maquina).get((Maquina_.nome))
				, root.get(Producao_.pessoa).get((Pessoa_.nome))));
		
		Predicate[]  predicates = criarRestricoes(producaoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoProducao> query  = entityManager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(pageable, query);
		
		return  new PageImpl<>(query.getResultList(), pageable, total(producaoFilter))  ;
	}
	
	
	

	private Predicate[] criarRestricoes(ProducaoFilter producaoFilter, CriteriaBuilder builder, Root<Producao> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!ObjectUtils.isEmpty(producaoFilter.getDescricao())) {
			predicates.add(builder.like(
					builder.lower(root.get(Producao_.descricao)), "%" + producaoFilter.getDescricao() + "%"));
		}
		
		if(producaoFilter.getDataProducaoDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Producao_.dataProducao), producaoFilter.getDataProducaoDe()));
		}
		
		if(producaoFilter.getDataProducaoAte() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Producao_.dataProducao), producaoFilter.getDataProducaoAte()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDePaginacao(Pageable pageable, TypedQuery<?> query) {
		int paginaAtual = pageable.getPageNumber();
		int totalDePaginaRegistrada = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalDePaginaRegistrada;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalDePaginaRegistrada);
	}
	
	private Long total(ProducaoFilter producaoFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Producao> root = criteria.from(Producao.class);
		
		
		Predicate[] predicates = criarRestricoes(producaoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return entityManager.createQuery(criteria).getSingleResult();
	}





}
