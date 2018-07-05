package com.vauban.vaubancommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.UNAUTHORIZED )
public class LoginException extends Exception {
	static final long serialVersionUID = -3387516993334229948L;

	public LoginException() {
		super( "check your Email and password and try again" );
	}

}
