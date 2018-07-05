package com.vauban.vaubancommerce.security;

public class TokenValidatorInterceptor { 

	
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
//		System.out.println( "---------------------------------------" );
//		System.out.println( request.getRequestURI() );
//		System.out.println( "---------------------------------------" );
//		if ( !UriEnum.contains( request.getRequestURI() ) ) {
//			return false;
//		} else {
//			return true;
//		}
//	}
//
//	private boolean reutrnError( HttpServletResponse response, int code, String msg ) {
//		try {
//			response.getWriter().write( msg );
//			response.setStatus( code );
//		} catch ( IOException e ) {
//			e.printStackTrace();
//		}
//		return false;
//	}
}
