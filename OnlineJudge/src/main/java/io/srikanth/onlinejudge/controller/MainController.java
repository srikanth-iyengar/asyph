package io.srikanth.onlinejudge.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.srikanth.onlinejudge.models.JudgeRequest;
import io.srikanth.onlinejudge.models.JudgeResponse;
import io.srikanth.onlinejudge.models.RunRequest;
import io.srikanth.onlinejudge.service.CodeRunner;

@RestController
public class MainController {
	final Logger logger = LogManager.getLogger(MainController.class);

	@Autowired
	private CodeRunner runner;

	@PostMapping("/judge")
	public JudgeResponse judgeCode(@RequestBody JudgeRequest request) throws IOException, InterruptedException {
		JudgeResponse response = runner.initiateRequest(request);
		runner.runJudge(request, response);
		return response;
	}

	@PostMapping("/run")
	public JudgeResponse runCode(@RequestBody RunRequest request) throws IOException, InterruptedException{
		JudgeResponse response = runner.initiateRunRequest(request);
		runner.runner(request, response);
		return response;
	}
}
