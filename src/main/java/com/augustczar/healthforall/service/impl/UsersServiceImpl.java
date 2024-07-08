package com.augustczar.healthforall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augustczar.healthforall.domain.Users;
import com.augustczar.healthforall.respositories.UsersRepository;
import com.augustczar.healthforall.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	UsersRepository usersRepository;

	@Override
	public void save(Users newUser) {
		usersRepository.save(newUser);
	}

	@Override
	public Object findByLogin(String login) {
		return usersRepository.findByLogin(login);
	}	
}
