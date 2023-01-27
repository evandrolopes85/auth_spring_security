package dev.evandro.auth.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("DEBUG - Requisição passou pelo filtro");
		
		// Recupero o cabeçalho
		Authentication auth = TokenUtil.decodeToken(request);
		
		// cabeçalho de autorizacao existe, preciso ver se eh valido
		if(request.getHeader("Authorization") != null) {
//			System.out.println(request.getHeader("Authorization"));
			if(auth != null) {
				// se o meu "token" for valida, eu passo a requisição para frente, indicando que ela está autenticada
				SecurityContextHolder.getContext().setAuthentication(auth);
			}else {
				// token existe mais não é valido - preciso customizar a minha mensagem de erro( não quero a mensagem vazia com erro 403)
				System.out.println("Erro no Token");
				ErroDTO erro = new ErroDTO(401, "Usuario nao autorizado para este sistema");
				response.setStatus(erro.getStatus());
				response.setContentType("application/json");
				ObjectMapper mapper = new ObjectMapper();
				response.getWriter().print(mapper.writeValueAsString(erro));
				response.getWriter().flush();
				return;
			}
			
		}
		
		// passa a requisição para frente
		filterChain.doFilter(request, response);
	}

}
