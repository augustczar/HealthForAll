package com.augustczar.healthforall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.augustczar.healthforall.component.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private SecurityFilter securityFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(HttpMethod.POST, "authuser/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/authuser/register").permitAll()
						.requestMatchers(HttpMethod.GET, "/api//beneficiary").hasRole("USER")
						.requestMatchers(HttpMethod.POST, "/api//beneficiary").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/api//beneficiary/*/update").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/api//beneficiary/*/delete").hasRole("ADMIN")
						.requestMatchers("*/v3/**", "/swagger-ui/**",  "/firstPlaceHealth").permitAll()
						.anyRequest().denyAll()
						//.anyRequest().authenticated()
						)
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
		
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
