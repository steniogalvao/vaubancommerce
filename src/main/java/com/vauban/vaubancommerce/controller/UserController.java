package com.vauban.vaubancommerce.controller;

import static com.vauban.vaubancommerce.utils.StaticStrings.ID;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vauban.vaubancommerce.exception.MandatoryField;
import com.vauban.vaubancommerce.exception.NotFoundEntity;
import com.vauban.vaubancommerce.model.User;
import com.vauban.vaubancommerce.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping( "/user/{userId}" )
	public User getUser( @PathVariable Long userId ) throws NotFoundEntity {
		Optional<User> response = userRepository.findById( userId );
		if ( response.isPresent() ) {
			return response.get();
		} else {
			throw new NotFoundEntity( ID, String.valueOf( userId ) );
		}
	}

	@PostMapping( "/user" )
	public ResponseEntity<Object> postUser( @Validated @RequestBody User user ) throws MandatoryField {
		if ( user.getPassword() == null || user.getPassword().isEmpty() ) {
			throw new MandatoryField( "password" );
		}
		return new ResponseEntity<Object>( userRepository.save( user ), HttpStatus.OK );
	}

	@PutMapping( "/user" )
	public User putUser( @Validated @RequestBody User user ) throws NotFoundEntity {
		if ( user.getId() == null ) {
			throw new NotFoundEntity( ID, String.valueOf( user.getId() ) );
		}
		Optional<User> response = userRepository.findById( user.getId() );
		user.setPassword( response.get().getPassword() );
		return userRepository.save( user );
	}

	@DeleteMapping( "/user/{userId}" )
	public void deleteUser( @PathVariable Long userId ) throws NotFoundEntity {
		Optional<User> response = userRepository.findById( userId );
		if ( !response.isPresent() ) {
			throw new NotFoundEntity( ID, String.valueOf( userId ) );
		} else {
			User user = response.get();
			user.setActive( false );
			userRepository.save( user );
		}
	}

}
