package com.vauban.vaubancommerce.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TokenValidatorInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion( HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3 ) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle( HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3 ) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
		System.out.println( "============================" );
		System.out.println( request.getRequestURI() );
		System.out.println( "============================" );
		// Token validation here, by URI
		return true;
	}

}
