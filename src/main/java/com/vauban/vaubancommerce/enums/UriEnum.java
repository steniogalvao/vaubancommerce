package com.vauban.vaubancommerce.enums;


public enum UriEnum {

//	CONFIGURATION_UI( "/configuration/ui" ),
//	RESET_PW( "/v2/user/resetpw" ),
//	NEW_PW( "/v2/user/newpw" ),
//	SWAGGER_RESOURCES( "/v2/swagger-resources" ),
//	API_DOCS( "/v2/api-docs" ),
//	CONFIGURATION_SECURITY( "/v2/configuration/security" ),
//	ERROR( "/v2/error" ),
	CREATE_USER( "/user/create" ),
	LOGIN( "/v2/login" );

	private String name;

	private UriEnum( String name ) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	};

	public static boolean contains( String uri ) {
		boolean contains = false;
		for ( UriEnum u : UriEnum.values() ) {
			if ( u.getName().equals( uri ) ) {
				contains = true;
				break;
			}
		}
		return contains;
	}
}
