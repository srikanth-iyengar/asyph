package io.company.usermicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.company.usermicroservice.models.AppUser;
import io.company.usermicroservice.models.UserRegisterRequest;
import io.company.usermicroservice.repository.AppUserRepository;

@Service
public class AppUserService {

	@Autowired
	private AppUserRepository appUserRepository;

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
}
