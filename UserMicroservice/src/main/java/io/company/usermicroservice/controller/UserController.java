package io.company.usermicroservice.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.company.usermicroservice.exception.ForbiddenException;
import io.company.usermicroservice.models.JudgeResponse;
import io.company.usermicroservice.models.Submission;
import io.company.usermicroservice.service.AppUserService;

@RestController
public class UserController {
	@Autowired
	private AppUserService appUserService;

	@Autowired
	private JWTVerifier jwtVerifier;

	final Logger logger = LogManager.getLogger(UserController.class);

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

