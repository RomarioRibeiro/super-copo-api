package com.exemple.supercopoapi.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.exemple.supercopoapi.config.property.SuperCopoApiProperty;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter{

	@Autowired
	private SuperCopoApiProperty superCopoApiProperty;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		res.setHeader("Access-Control-Allow-Origin", superCopoApiProperty.getOriginPermitida());
		res.setHeader("Access-Control-Allow-Credentials", "true");
		
		if("OPTIONS".equals(req.getMethod()) && superCopoApiProperty.getOriginPermitida().equals(req.getHeader("Origin")) ) {
			res.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
			res.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
			res.setHeader("Access-Control-Max-Age", "3600");
			
			
			res.setStatus(HttpServletResponse.SC_OK);
		}else {
			chain.doFilter(request, response);
		}
		
	}

}
