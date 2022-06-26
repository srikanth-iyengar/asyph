package io.company.usermicroservice.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import io.company.usermicroservice.service.AppUserService;
import io.company.usermicroservice.utils.JwtRequestFilter;

@Service
public class SecurityConfiguration {
	
	final Logger logger = LogManager.getLogger(SecurityConfiguration.class);

	@Autowired
	private AppUserService appUserService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	AuthenticationManager authenticationManager;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authz) -> {
			try {
				authz.anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		logger.info("Security filter chain bean creation successfull");
		return http.build();
	}
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
		logger.info("Disabling security in some endpoints");
        return (web) -> web.ignoring().antMatchers("/authenticate", "/register");
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(appUserService);
		return provider;
	}
}
