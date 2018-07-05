package com.vauban.vaubancommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vauban.vaubancommerce.exception.LoginException;
import com.vauban.vaubancommerce.exception.NotFoundEntity;
import com.vauban.vaubancommerce.model.Login;
import com.vauban.vaubancommerce.model.User;
import com.vauban.vaubancommerce.repository.UserRepository;

@RestController
public class LoginController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping( "/login" )
	public String getProduct( @Validated @RequestBody Login login ) throws NotFoundEntity, LoginException {
		User user = userRepository.findByEmailAndPassword( login.getEmail(), login.getPassword() );
		if ( user == null ) {
			throw new LoginException();
		}
		//return token with jwt
		return null;
	}

}
