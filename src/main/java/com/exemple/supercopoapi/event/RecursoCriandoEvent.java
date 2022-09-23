package com.exemple.supercopoapi.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursoCriandoEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	private Long codigo;

	public RecursoCriandoEvent(Object source, HttpServletResponse response, Long codigo) {
		super(source);
		this.codigo = codigo;
		this.response = response;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getCodigo() {
		return codigo;
	}
	
	
}
