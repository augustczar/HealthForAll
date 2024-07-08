package com.augustczar.healthforall.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.augustczar.healthforall.domain.Users;
import com.augustczar.healthforall.dtos.AuthenticationDto;
import com.augustczar.healthforall.dtos.LoginResponseDto;
import com.augustczar.healthforall.dtos.RegisterDto;
import com.augustczar.healthforall.service.TokenService;
import com.augustczar.healthforall.service.UsersService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/authuser")
public class AuthenticationController {

	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsersService  usersService;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto authenticationDto) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.login(), authenticationDto.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generationToken((User) auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDto(token.toString()));
	}
	
	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody @Valid RegisterDto registerDto){
		
		if (usersService.findByLogin(registerDto.login()) != null)
			return ResponseEntity.badRequest().build();
		
		String encryptedPasswword = new  BCryptPasswordEncoder().encode(registerDto.password());
		Users newUser = new Users(registerDto.login(), encryptedPasswword, registerDto.role());
		
		this.usersService.save(newUser);
		
		return ResponseEntity.ok().build();
	}
}
