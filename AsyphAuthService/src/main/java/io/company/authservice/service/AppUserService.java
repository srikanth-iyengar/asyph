package io.company.authservice.service;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.company.authservice.models.AppUser;
import io.company.authservice.models.EmailActivation;
import io.company.authservice.models.UserRegisterRequest;
import io.company.authservice.repository.AppUserRepository;
import io.company.authservice.repository.EmailActivationRepository;

@Service
public class AppUserService implements UserDetailsService {

	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private EmailActivationRepository emailActivationrRepository;

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
		appUserRepository.save(user);
		sendActivationLink(user);
		return true;
	}

	public void sendActivationLink(AppUser appUser) {
		String token = UUID.randomUUID().toString().replace("-", "");
		EmailActivation emailActivation = new EmailActivation(token, appUser.getUsername());
		System.out.println("Email Activation: " + emailActivation);
		emailActivationrRepository.save(emailActivation);
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ksrikanth200212@gmail.com");
		message.setTo(appUser.getEmailId());
		message.setSubject("Account activation for Asyph-OJ");
		message.setText("Here is your activation link for asyph-oj click here: " + "https://localhost:8083/activate?token=" + token + "&username=" + appUser.getUsername() + "\n" + "Regards, \n" + "Srikanth Iyengar, Bhuvanesh Trivedi\n" + "Asyph-OJ Team");
		javaMailSender.send(message);
	}

	public Boolean checkToken(String token, LocalDateTime time, String username) {
		EmailActivation emailActivation = emailActivationrRepository.findById(username).get();
		if(emailActivationrRepository == null || !token.equals(emailActivation.getToken())) {
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
}
