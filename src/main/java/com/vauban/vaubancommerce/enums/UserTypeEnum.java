package com.vauban.vaubancommerce.enums;

public enum UserTypeEnum {

	ADMIN( "admin" ),
	USER( "user" );

	@SuppressWarnings( "unused" )
	private String type;

	UserTypeEnum( String type ) {
		this.type = type;
	}

}
