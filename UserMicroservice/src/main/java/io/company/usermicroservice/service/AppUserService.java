package io.company.usermicroservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import io.company.usermicroservice.models.AppUser;
import io.company.usermicroservice.models.JudgeRequest;
import io.company.usermicroservice.models.JudgeResponse;
import io.company.usermicroservice.models.Problems;
import io.company.usermicroservice.models.Submission;
import io.company.usermicroservice.models.UserRegisterRequest;
import io.company.usermicroservice.models.Verdict;
import io.company.usermicroservice.repository.AppUserRepository;
import io.company.usermicroservice.repository.SubmissionRepository;
import reactor.core.publisher.Mono;

@Service
public class AppUserService {

	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@Autowired
	private SubmissionRepository submissionRepository;

	public boolean registerUser(UserRegisterRequest request) {
		AppUser alreadyRegister = appUserRepository.findByUsername(request.emailId);
		// Checking if the user is already registered
		if(alreadyRegister != null) {
			return false;
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = encoder.encode(request.password);
		AppUser user = new AppUser.Builder()
				.username(request.username)
				.emailId(request.emailId)
				.firstName(request.firstName)
				.lastName(request.lastName)
				.password(password)
				.build();
		appUserRepository.save(user);
		return true;
	}

	private Problems fetchProblem(String id) {
		Problems problem = webClientBuilder.build()
			.get()
			.uri("http://PROBLEM-CONTEST-SERVICE/get-problem/"+id)
			.retrieve()
			.bodyToMono(Problems.class)
			.block();
		return problem;
	}

	public Submission submitCodeToJudge(Submission request) {
		Problems problem = fetchProblem(request.getProblemId());
		if(problem == null) {
			return new Submission();
		}
		String username = request.getKey().getUsername();
		AppUser user = appUserRepository.findByUsername(username);
		if(user == null) {
			request.setVerdict(Verdict.valueOf("IGNORED"));
			request.setNote("USER NOT FOUND. PLEASE REGISTER TO SUBMIT CODE");
			return request;
		}
		JudgeRequest requestBody = new JudgeRequest();
		requestBody.setCode(request.getCode());
		requestBody.setProblemId(request.getProblemId());
		requestBody.setTestCases(problem.getTestCases());
		requestBody.setTimeLimit(problem.getTimeLimit());
		requestBody.setCompiler(request.getLanguage().toString());
		requestBody.setContestId(request.getContestId());
		JudgeResponse response = webClientBuilder.build()
			.post()
			.uri("http://online-judge/judge")
			.contentType(MediaType.APPLICATION_JSON)
			.body(Mono.just(requestBody), JudgeRequest.class)
			.retrieve()
			.bodyToMono(JudgeResponse.class)
			.block();
		request.getKey().setId(response.getToken());
		request.setVerdict(response.getVerdict());
		request.setNote(response.getNote());
		request.setExecutionTime(response.getExecutionTime());
		submissionRepository.save(request);
		return request;
	}

	public List<Submission> getSubmissionByUsername(String username) {
		return submissionRepository.findByKeyUsername(username);
	}

	public List<Submission> getInContestSubmissions(String username, String contestId) {
		return submissionRepository.findByKeyUsernameAndContestId(username, contestId);
	}

	public void updateSubmission(JudgeResponse response) {
		Submission submission = submissionRepository.findByKeyId(response.getToken());
		submission.setVerdict(response.getVerdict());
		submission.setNote(response.getNote());
		submission.setExecutionTime(response.getExecutionTime());
		submissionRepository.save(submission);
	}
}
