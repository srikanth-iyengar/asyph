package io.asyph.problemcontestservice.configuration;

import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class SchedulerConfiguration {

	@Bean
	public SchedulerFactory getSchedulerFactory() throws SchedulerException{
		SchedulerFactory factory = new StdSchedulerFactory();
		factory.getScheduler().start();
		return factory;
	}
}
