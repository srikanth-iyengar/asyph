package io.company.authservice.Startup;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import io.company.authservice.models.AppUser;
import io.company.authservice.repository.AppUserRepository;

@Component
public class CommandLineStartupRunner implements CommandLineRunner {
	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void run(String ...args) throws Exception {
		String secretKey = UUID.randomUUID().toString();
		String password = new BCryptPasswordEncoder().encode(secretKey);
		AppUser admin = new AppUser.Builder().firstName("srikanth").lastName("iyengar").username("srikanth_headquaters").password(password).emailId("ksrikanth200212@gmail.com").build();
		admin.setIsEnabled(true);
		admin.setRole("ADMIN");
		appUserRepository.save(admin);

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ksrikanth200212@gmail.com");
		message.setTo("ksrikanth200212@gmail.com");
		message.setSubject("Server Restarted/Started" );
		message.setText("Server started from crash state or It is up for running for the first time" + secretKey);
		javaMailSender.send(message);
	}
}
