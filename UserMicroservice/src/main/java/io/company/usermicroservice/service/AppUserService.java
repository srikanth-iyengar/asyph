package io.company.usermicroservice.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import io.company.usermicroservice.models.AppUser;
import io.company.usermicroservice.models.EmailActivation;
import io.company.usermicroservice.models.JudgeRequest;
import io.company.usermicroservice.models.JudgeResponse;
import io.company.usermicroservice.models.Problems;
import io.company.usermicroservice.models.Submission;
import io.company.usermicroservice.models.UpdateLeaderboard;
import io.company.usermicroservice.models.UserRegisterRequest;
import io.company.usermicroservice.models.Verdict;
import io.company.usermicroservice.repository.AppUserRepository;
import io.company.usermicroservice.repository.EmailActivationRepository;
import io.company.usermicroservice.repository.SubmissionRepository;
import reactor.core.publisher.Mono;

@Service
public class AppUserService implements UserDetailsService {

	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@Autowired
	private SubmissionRepository submissionRepository;


	@Autowired
	private EmailActivationRepository emailActivationRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = appUserRepository.findByUsername(username);
		return appUser;
	}

	public boolean registerUser(UserRegisterRequest request) {
		AppUser alreadyRegister = appUserRepository.findByUsername(request.username);
		
		// Checking if the user is already registered
		if(alreadyRegister != null) {
			return false;
		}
		alreadyRegister = appUserRepository.findByEmailId(request.emailId);
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
		user.setRole("USER");
		appUserRepository.save(user);
		sendActivationLink(user);
		return true;
	}

	public void updatePrivilige(String username) {
		AppUser user = appUserRepository.findByUsername(username);
		user.setRole("PROBLEM_SETTER");
		appUserRepository.save(user);
	}

	public void blockUser(String username) {
		AppUser user = appUserRepository.findByUsername(username);
		user.setIsLocked(true);
		appUserRepository.save(user);
	}

	public void sendActivationLink(AppUser appUser) {
		String token = UUID.randomUUID().toString().replace("-", "");
		EmailActivation emailActivation = new EmailActivation(token, appUser.getUsername());
		emailActivationRepository.save(emailActivation);
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ksrikanth200212@gmail.com");
		message.setTo(appUser.getEmailId());
		message.setSubject("Account activation for Asyph-OJ");
		message.setText("Here is your activation link for asyph-oj click here: " + "https://localhost:8083/activate?token=" + token + "&username=" + appUser.getUsername() + "\n" + "Regards, \n" + "Srikanth Iyengar\n" + "Asyph-OJ Team");
		javaMailSender.send(message);
	}

	public Boolean checkToken(String token, LocalDateTime time, String username) {
		EmailActivation emailActivation = emailActivationRepository.findById(username).get();
		if(emailActivation == null || !token.equals(emailActivation.getToken())) {
			regenerateToken(username);
			return false;
		}
		
		Duration dur = Duration.between(emailActivation.getGeneratedAt(), time);
		if(dur.getSeconds() <= 30 * 60) {
			AppUser appUser = appUserRepository.findByUsername(username);
			appUser.setIsEnabled(Boolean.valueOf(true));
			appUserRepository.save(appUser);
			return true;
		}
		regenerateToken(username);
		return false;
	}

	public void regenerateToken(String username) {
		AppUser appUser = appUserRepository.findByUsername(username);
		sendActivationLink(appUser);
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
		request.setSubmissionTime(LocalDateTime.now());
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
		if(response.getVerdict().equals(Verdict.COMPILATION_ERROR)) return;
		UpdateLeaderboard leaderboard = new UpdateLeaderboard(submission.getKey().getUsername(), submission.getProblemId(), submission.getContestId(), submission.getVerdict().equals(Verdict.ACCEPTED));
		webClientBuilder.build()
			.put()
			.uri("http://PROBLEM-CONTEST-SERVICE/update-leaderboard")
			.contentType(MediaType.APPLICATION_JSON)
			.body(Mono.just(leaderboard), UpdateLeaderboard.class)
			.retrieve().bodyToMono(Object.class).block();
	}
}
