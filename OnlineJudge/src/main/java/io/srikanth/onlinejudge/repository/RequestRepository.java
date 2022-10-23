package io.srikanth.onlinejudge.repository;

import org.springframework.data.repository.CrudRepository;

import io.srikanth.onlinejudge.models.JudgeResponse;

public interface RequestRepository extends CrudRepository<JudgeResponse, String> {
	
}
