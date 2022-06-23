package io.company.asyphdiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AsyphDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyphDiscoveryServerApplication.class, args);
	}

}
