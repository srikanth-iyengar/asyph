package io.asyph.gateway.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

@EnableWebFluxSecurity
public class SecurityConfiguration {
    
    @Autowired
    private JwtSecurityFilter jwtSecurityFilter;
    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(exchanges -> {
            exchanges.pathMatchers("/api/v1/user/register", "/api/v1/user/authenticate").permitAll();
            exchanges.pathMatchers("/api/v1/Problems-Contest-Service/**").hasAnyAuthority("ADMIN", "PROBLEM_SETTER");
        });
        http.securityContextRepository(NoOpServerSecurityContextRepository.getInstance());
        http.addFilterAt(jwtSecurityFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        return http.build();
    }
}
