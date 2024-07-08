package com.augustczar.healthforall.service;

import org.springframework.stereotype.Service;

import com.augustczar.healthforall.domain.Users;

public interface UsersService {

	void save(Users newUser);

	Object findByLogin(String login);
}
