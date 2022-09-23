package com.exemple.supercopoapi.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.exemple.supercopoapi.event.RecursoCriandoEvent;

public class RecursoCriandoListener implements ApplicationListener<RecursoCriandoEvent> {

	@Override
	public void onApplicationEvent(RecursoCriandoEvent recursoCriandoEvent) {
		HttpServletResponse response = recursoCriandoEvent.getResponse();
		Long codigo = recursoCriandoEvent.getCodigo();
		
		
		adicionarHeaderLocation(response, codigo);
		
		
	}

	private void adicionarHeaderLocation(HttpServletResponse response, Long codigo) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(codigo).toUri();
				response.setHeader("Location", uri.toASCIIString());
	}

}
