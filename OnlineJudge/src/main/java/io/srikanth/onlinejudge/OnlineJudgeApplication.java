package io.srikanth.onlinejudge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableAsync
public class OnlineJudgeApplication {

	final Logger logger = LogManager.getLogger(OnlineJudgeApplication.class);

	@Bean(name = "codeJudger")
	public TaskExecutor workExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setThreadNamePrefix("Judge-");
		threadPoolTaskExecutor.setCorePoolSize(10);
		threadPoolTaskExecutor.setMaxPoolSize(12);
		threadPoolTaskExecutor.setQueueCapacity(600);
		logger.info("Judge pool initialized");
		return threadPoolTaskExecutor;
	}

	@Bean(name = "judgeRunner")
	public TaskExecutor codeRunner() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setThreadNamePrefix("Runner-");
		threadPoolTaskExecutor.setCorePoolSize(3);
		threadPoolTaskExecutor.setMaxPoolSize(6);
		threadPoolTaskExecutor.setQueueCapacity(300);
		logger.info("Runner pool initialized");
		return threadPoolTaskExecutor;
	}

	@Bean(name = "sendSubmissionStatus")
	public TaskExecutor sendSubmissionStatus() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setThreadNamePrefix("SubmissionUpdate-");
		threadPoolTaskExecutor.setCorePoolSize(10);
		threadPoolTaskExecutor.setMaxPoolSize(12);
		threadPoolTaskExecutor.setQueueCapacity(600);
		return threadPoolTaskExecutor;
	}

	@Bean
	@LoadBalanced
	public WebClient.Builder getWebClientBuilder() {
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(OnlineJudgeApplication.class, args);
	}

}
