package com.augustczar.healthforall.respositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.augustczar.healthforall.domain.Users;

public interface UsersRepository extends JpaRepository<Users, UUID>{
	 
		UserDetails findByLogin(String login);	
}
