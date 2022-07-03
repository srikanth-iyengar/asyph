package io.company.usermicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.company.usermicroservice.service.AppUserService;

@RestController
public class UserController {
	@Autowired
	private AppUserService appUserService;

	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
}
