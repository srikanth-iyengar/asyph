package io.company.usermicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.company.usermicroservice.models.JudgeResponse;
import io.company.usermicroservice.models.Submission;
import io.company.usermicroservice.service.AppUserService;

@RestController
public class UserController {
	@Autowired
	private AppUserService appUserService;


	@PostMapping("/submit-code")
	public JudgeResponse submitCode(@RequestBody Submission request) {
		return appUserService.submitCodeToJudge(request);
	}
}
