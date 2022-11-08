package io.asyph.gateway.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.session.WebSessionManager;

import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityContextRepository securityContextRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        return 
            http.csrf().disable()
            .authenticationManager(authenticationManager)
            .securityContextRepository(securityContextRepository)
            .authorizeExchange()
            .pathMatchers("/api/v1/user/register", 
                    "/api/v1/user/authenticate", 
                    "/api/v1/user/swagger-ui/**", 
                    "/api/v1/user/v3/api-docs/**",
                    "/api/v1/Problems-Contest-Service/swagger-ui/**", 
                    "/api/v1/Problems-Contest-Service/v3/api-docs/**",
                    "/api/v1/user/latest-blogs"
                    )
            .permitAll()
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
