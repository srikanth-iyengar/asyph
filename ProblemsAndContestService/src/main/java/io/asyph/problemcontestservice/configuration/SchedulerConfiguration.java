package io.asyph.problemcontestservice.configuration;

import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class SchedulerConfiguration {
	

	@Bean
	public SchedulerFactory getSchedulerFactory() throws SchedulerException{
		SchedulerFactory factory = new StdSchedulerFactory();
		factory.getScheduler().start();
		return factory;
	}
}
