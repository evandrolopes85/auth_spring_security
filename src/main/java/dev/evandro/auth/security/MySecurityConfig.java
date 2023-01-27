package dev.evandro.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf().disable() 		  	// desabilito o csrf( por que eu que vou tratar a autentificação dos usuarios)
			.authorizeHttpRequests()	// agora as requisições http sao passiveis de autorização
			.requestMatchers(HttpMethod.GET, "/free").permitAll()   // para o método GET no endpoint free, libera todo mundo
			.anyRequest().authenticated().and().cors(); 			// Todas as outras URLs terao a necessidade de autenticacao ( e sofrerao as restricao do CORS)
		
		// anes do filter de user name e coloco o meu filtro
		// O MyFilter vai vir antes do UsernamePasswordAuthenticationFilter que é a telinha de autenticacao padrao
		http.addFilterBefore(new MyFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build(); // retorna essa configuracao para o container do spring
			
	}
}
