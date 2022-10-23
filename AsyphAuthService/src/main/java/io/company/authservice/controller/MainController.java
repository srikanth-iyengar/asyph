package io.company.authservice.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import io.company.authservice.models.AuthenticationRequest;
import io.company.authservice.models.AuthenticationResponse;
import io.company.authservice.models.UserRegisterRequest;
import io.company.authservice.service.AppUserService;

@RestController
public class MainController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AppUserService appUserService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest request) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username, request.password));
		}
		catch(BadCredentialsException e) {
			throw new Exception("Bad credentials", e);
		}
		final UserDetails userDetails = appUserService.loadUserByUsername(request.username);
		
		Algorithm algorithm = Algorithm.HMAC512("secret".getBytes());
		String jwtToken = JWT.create().withSubject(request.username)
							.withExpiresAt(new Date(System.currentTimeMillis() + 1000*60*60))
							.withIssuer("Srikanth Iyengar")
							.withClaim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
							.sign(algorithm);
		return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
	}
	
	
	@PostMapping("/register")
	public Boolean registerUser(@RequestBody UserRegisterRequest request) {
		Boolean response = appUserService.registerUser(request);
		return response;
	}

	@PutMapping("/activate")
	public Boolean activateAccount(@RequestParam String token,@RequestParam String username) {
		System.out.println(token + " " + username);
		return appUserService.checkToken(token, LocalDateTime.now(), username);
	}
	
	@GetMapping("hello")
	public String getHello() {
		return "hello";
	}
}
