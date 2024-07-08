package com.augustczar.healthforall.service;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

public interface TokenService {

	String validateToken(String token);

	Object generationToken(User principal);
}
