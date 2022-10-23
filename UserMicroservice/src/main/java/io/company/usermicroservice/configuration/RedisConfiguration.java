package io.company.usermicroservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

@Configuration
public class RedisConfiguration {

    @Bean
    public JWTVerifier jwtVerifier() {
        Algorithm algorithm = Algorithm.HMAC512("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
				return verifier;
    }
}
