package io.company.usermicroservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.company.usermicroservice.exception.ForbiddenException;
import io.company.usermicroservice.models.AuthenticationRequest;
import io.company.usermicroservice.models.JudgeResponse;
import io.company.usermicroservice.models.Submission;
import io.company.usermicroservice.models.UserRegisterRequest;
import io.company.usermicroservice.service.AppUserService;

@RestController
public class UserController {
	@Autowired
	private AppUserService appUserService;

	@Autowired
	private JWTVerifier jwtVerifier;


	@Autowired
	private AuthenticationManager authenticationManager;


	@GetMapping("/ping")
	public String ping() {
		return "PONG";
	}
	@PostMapping("/submit-code")
	public Submission submitCode(@RequestBody Submission request, @RequestHeader Map<String, String> headers) throws ForbiddenException {
		String token = headers.get("authorization1").substring("Bearer ".length());
		if(!check(token, request.getKey().getUsername())) {
			throw new ForbiddenException();
		}
		return appUserService.submitCodeToJudge(request);
	}

	@PostMapping("/register") 
	public void registerUser(@RequestBody UserRegisterRequest req) {
		appUserService.registerUser(req);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest request) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		}
		catch(BadCredentialsException e) {
			throw new Exception("Bad credentials", e);
		}
		final UserDetails userDetails = appUserService.loadUserByUsername(request.getUsername());
		
		Algorithm algorithm = Algorithm.HMAC512("secret".getBytes());
		String jwtToken = JWT.create().withSubject(request.getUsername())
							.withExpiresAt(new Date(System.currentTimeMillis() + 100000*60*60))
							.withIssuer("Srikanth Iyengar")
							.withClaim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
							.sign(algorithm);
		return ResponseEntity.ok(jwtToken);
	}

	
	@GetMapping("/get-submissions/{username}")
	public List<Submission> getSubmissions(@PathVariable String username, @RequestHeader Map<String, String> headers) {
		String token = headers.get("authorization1").substring("Bearer ".length());
		if(!check(token, username)) {
			throw new ForbiddenException();
		}
		return appUserService.getSubmissionByUsername(username);
	}

	@GetMapping("/get-contest-submission")
	public List<Submission> getSubmissionByContest(@RequestParam String username, @RequestParam String contestId, @RequestHeader Map<String , String> headers){
		String token = headers.get("authorization1").substring("Bearer ".length());
		if(!check(token, username)) {
			throw new ForbiddenException();
		}
		return appUserService.getInContestSubmissions(username, contestId);
	}

	@PutMapping("/update-submission")
	public void updateSubmission(@RequestBody JudgeResponse response) {
		appUserService.updateSubmission(response);
	}

	/* function to check the jwt and the username argument passed */
	public Boolean check(String token, String username) {
		DecodedJWT decoded = jwtVerifier.verify(token);
		String subject = decoded.getSubject();
		return username.equals(subject);
	}
}

