package com.exemple.supercopoapi.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.supercopoapi.event.RecursoCriandoEvent;
import com.exemple.supercopoapi.exceptionhandler.SupercopoapiExecptionhandler.Erro;
import com.exemple.supercopoapi.model.Producao;
import com.exemple.supercopoapi.repository.ProducaoRepository;
import com.exemple.supercopoapi.repository.filter.ProducaoFilter;
import com.exemple.supercopoapi.repository.projection.ResumoProducao;
import com.exemple.supercopoapi.service.ProducaoService;
import com.exemple.supercopoapi.service.exception.PessoaInexistenteOuInativa;

@RestController
@RequestMapping("/producaos")
public class ProducaoResource {

	@Autowired
	private ProducaoRepository producaoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ProducaoService producaoService;
	
	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PRODUCAO') and hasAuthority('SCOPE_read')" )
	public Page<Producao> pesquisar(ProducaoFilter producaoFilter, Pageable pageable) {
		return producaoRepository.filtrar(producaoFilter, pageable);
	}
	
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PRODUCAO') and hasAuthority('SCOPE_read')" )
	public Page<ResumoProducao> resumir(ProducaoFilter producaoFilter, Pageable pageable) {
		return producaoRepository.resumir(producaoFilter, pageable);
	}
	
	
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PRODUCAO') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Producao> buscar(@PathVariable Long codigo) {
		return producaoRepository.findById(codigo)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PRODUCAO') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Producao> criar(@Valid @RequestBody Producao producao, HttpServletResponse response) {
		Producao producaoSalva = producaoService.salvar(producao);
		publisher.publishEvent(new RecursoCriandoEvent(this, response, producaoSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(producaoSalva);
		
	}
	
	
	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PRODUCAO') and hasAuthority('SCOPE_read')" )
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long codigo) {
		producaoRepository.deleteById(codigo);
	}
	
	@ExceptionHandler(PessoaInexistenteOuInativa.class)
	public ResponseEntity<Object> handlePessoaInexistenteOuInativa(PessoaInexistenteOuInativa ex) {
		
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());	
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	
}
