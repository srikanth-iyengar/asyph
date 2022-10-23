package io.company.authservice.models;

public class AuthenticationResponse {
	public String jwt;
	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}
}
