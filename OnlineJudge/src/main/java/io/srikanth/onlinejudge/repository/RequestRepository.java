package io.srikanth.onlinejudge.repository;

import org.springframework.data.repository.CrudRepository;

import io.srikanth.onlinejudge.models.RunnerResponse;

public interface RequestRepository extends CrudRepository<RunnerResponse, String> {
	
}
