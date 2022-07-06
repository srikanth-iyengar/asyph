package io.company.usermicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import io.company.usermicroservice.models.AppUser;
import io.company.usermicroservice.models.JudgeRequest;
import io.company.usermicroservice.models.JudgeResponse;
import io.company.usermicroservice.models.Submission;
import io.company.usermicroservice.models.UserRegisterRequest;
import io.company.usermicroservice.repository.AppUserRepository;
import reactor.core.publisher.Mono;

@Service
public class AppUserService {

	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired 
	private WebClient.Builder webClientBuilder;


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

	public JudgeResponse submitCodeToJudge(Submission request) {
		JudgeRequest requestBody = new JudgeRequest();
		requestBody.setCode(request.getCode());
		requestBody.setProblemId(request.getProblemId());
		requestBody.setTestCases(1);
		requestBody.setTimeLimit(2);
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
		return response;
	}
}
