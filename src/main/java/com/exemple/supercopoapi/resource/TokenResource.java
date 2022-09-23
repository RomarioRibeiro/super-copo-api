package com.exemple.supercopoapi.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.supercopoapi.config.property.SuperCopoApiProperty;

@RestController
@RequestMapping("/tokens")
public class TokenResource {
	
	@Autowired
	private SuperCopoApiProperty superCopoApiProperty;
	
	
	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest res, HttpServletResponse resp) {
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(superCopoApiProperty.getSeguranca().isEnableHttps()); 
		cookie.setPath(res.getContextPath() + "/oauth/token");
		cookie.setMaxAge(0);
		
		
		resp.addCookie(cookie);
		resp.setStatus(HttpStatus.NO_CONTENT.value());
		
		
	}
	
}
