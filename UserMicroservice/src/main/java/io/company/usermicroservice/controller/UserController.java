package io.company.usermicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.company.usermicroservice.models.AuthenticationRequest;
import io.company.usermicroservice.models.UserRegisterRequest;
import io.company.usermicroservice.service.AppUserService;
import io.company.usermicroservice.utils.JwtTokenUtil;

@RestController
public class UserController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AppUserService appUserService;

	@Autowired
	private JwtTokenUtil jwtUtil;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect Username or Passowrd", e);
		}

		final UserDetails userDetails = appUserService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(jwt);
	}
	
	@PostMapping("/register")
	public Boolean registerUser(@RequestBody UserRegisterRequest request) {
		Boolean response = appUserService.registerUser(request);
		return response;
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
}
