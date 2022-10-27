package io.asyph.gateway.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.server.session.WebSessionManager;

import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityContextRepository securityContextRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtSecurityFilter jwtSecurityFilter;
    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        return 
            http.csrf().disable()
            .authenticationManager(authenticationManager)
            .securityContextRepository(securityContextRepository)
            .authorizeExchange()
            .pathMatchers("/api/v1/user/register", "/api/v1/user/authenticate").permitAll()
            .pathMatchers("/api/v1/user/admin/**").hasAuthority("ADMIN")
            .pathMatchers("/api/v1/Problems-Contest-Service").hasAnyAuthority("ADMIN", "PROBLEM_SETTER")
            .anyExchange()
            .authenticated()
            .and()
            .build();
    }

    @Bean
    public WebSessionManager webSessionManager() {
        return exchange -> Mono.empty();
    }
}
