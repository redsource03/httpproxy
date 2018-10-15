package com.redsource.samples.httpproxy.wrapper;

public enum EndPointEnum {
	TEST("/input");
	
	EndPointEnum(String endpoint){
		this.endpoint= endpoint;
	}
	
	private String endpoint;

	public String getEndpoint() {
		return endpoint;
	}
	
	
}
