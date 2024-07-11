package com.augustczar.healthforall.service;

import com.augustczar.healthforall.domain.Users;

public interface TokenService {

	String validateToken(String token);

	Object generationToken(Users principal);
}
