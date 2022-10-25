package io.company.usermicroservice.models;

public class UserRegisterRequest {
	public String username;
	public String emailId;
	public String password;
	public String firstName;
	public String lastName;


	public UserRegisterRequest(String username, String emailId, String password, String firstName, String lastName) {
		this.username = username;
		this.emailId = emailId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
