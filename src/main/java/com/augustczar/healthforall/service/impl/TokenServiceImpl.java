package com.augustczar.healthforall.service.impl;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.augustczar.healthforall.domain.Users;
import com.augustczar.healthforall.service.TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenServiceImpl implements TokenService{

	@Value("${api.security.tocken.secret}")
	private String SECRET;

//	@Value("${api.generates.expiration.hours}")
//	private long HOURS;
	
	public String generationToken(Users usersModel) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			String token = JWT.create()
					.withIssuer("auth-api")
					.withSubject(usersModel.getLogin())
					.withExpiresAt(generatesExpirationDate())
					.sign(algorithm);
			return token;
		} catch (JWTCreationException creationException) {
			throw new RuntimeException("Error while generating token", creationException);
		}
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			return JWT.require(algorithm)
					.withIssuer("auth-api")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException verificationException) {
			return "";
		}
	}
	
	private Instant generatesExpirationDate() {
		return LocalDateTime.now().plusMinutes(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
