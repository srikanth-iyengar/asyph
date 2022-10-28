package io.asyph.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("r1", r -> r
													.path("/api/v1/user/**")
													.uri("lb://USER-SERVICE")
						)
				.route("r1", r -> r
													.path("/api/v1/Problems-Contest-Service/**")
													.uri("lb://PROBLEMS-CONTEST-SERVICE")
						)
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
}
