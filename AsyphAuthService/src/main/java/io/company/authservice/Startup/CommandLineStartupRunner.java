package io.company.authservice.Startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import io.company.authservice.models.AppUser;
import io.company.authservice.repository.AppUserRepository;

@Component
public class CommandLineStartupRunner implements CommandLineRunner {
	@Autowired
	private AppUserRepository appUserRepository;

	@Override
	public void run(String ...args) throws Exception {
		String password = new BCryptPasswordEncoder().encode("1234");
		AppUser admin = new AppUser.Builder().firstName("srikanth").lastName("iyengar").username("srikanth_headquaters").password(password).emailId("ksrikanth3012@gmail.com").build();
		admin.setRole("ADMIN");
		appUserRepository.save(admin);
	}
}
