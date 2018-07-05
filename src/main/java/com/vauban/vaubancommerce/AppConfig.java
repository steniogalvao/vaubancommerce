package com.vauban.vaubancommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.vauban.vaubancommerce.security.TokenValidatorInterceptor;

@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

	@Autowired
	TokenValidatorInterceptor tokenInterceptor;

	@Override
	public void addInterceptors( InterceptorRegistry registry ) {
		registry.addInterceptor( tokenInterceptor );
	}
}