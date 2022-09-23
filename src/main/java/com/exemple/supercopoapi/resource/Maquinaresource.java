package com.exemple.supercopoapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.supercopoapi.event.RecursoCriandoEvent;
import com.exemple.supercopoapi.model.Maquina;
import com.exemple.supercopoapi.repository.MaquinaRepository;

@RestController
@RequestMapping("/maquinas")
public class Maquinaresource {
	
	@Autowired
	private MaquinaRepository maquinaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping
	public List<Maquina> listar() {
		return maquinaRepository.findAll();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_MAQUINA') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Maquina> criar(@Valid @RequestBody Maquina maquina, HttpServletResponse response){
		Maquina maquinaSalva = maquinaRepository.save(maquina);
		
		publisher.publishEvent(new RecursoCriandoEvent(this, response, maquinaSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(maquinaSalva);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MAQUINA') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Maquina> buscar(@PathVariable Long codigo){
		return maquinaRepository.findById(codigo)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		
		
	}
}
