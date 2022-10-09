package io.company.authservice.repository;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;


@Repository
public class TraceRepository {
	@Bean
	public HttpTraceRepository httpTraceRepository() {
		return new InMemoryHttpTraceRepository();
	}
}
