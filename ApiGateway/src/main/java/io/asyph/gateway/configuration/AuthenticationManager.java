package io.asyph.gateway.configuration;

import static java.util.Arrays.stream;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {
    @Override
    public Mono authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        Algorithm algorithm = Algorithm.HMAC512("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(authToken);
        String username;
        try {
            username = decodedJWT.getSubject();
        }
        catch(Exception e) {
            username = null;
        }
        if(username != null && decodedJWT.getExpiresAt().after(Date.from(Instant.now()))){
            String roles[] = decodedJWT.getClaim("roles").asArray(String.class);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            stream(roles).forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role));
            });
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, username, authorities);
            return Mono.just(auth);
        }
        return Mono.empty();
    }
}
