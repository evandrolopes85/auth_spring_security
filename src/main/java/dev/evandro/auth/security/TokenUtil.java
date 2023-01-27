package dev.evandro.auth.security;

import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;

public class TokenUtil {
	
	public static Authentication decodeToken(HttpServletRequest request){
		if(request.getHeader("Authorization").equals("Bearer *Evandro123")) {
			// caso a requisicao tenha o cabecalho correto eu gero um "token" interno" com as informacoes que eu considero relevante
			return new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList());
		}
		return null;
	}
}
