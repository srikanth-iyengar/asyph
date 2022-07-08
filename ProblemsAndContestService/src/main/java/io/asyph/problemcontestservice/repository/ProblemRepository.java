package io.asyph.problemcontestservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import io.asyph.problemcontestservice.models.Problems;

public interface ProblemRepository extends CassandraRepository<Problems, String>{
	Optional<Problems> findByProblemId(String problemId);
	
	@AllowFiltering
	List<Problems> findByContestId(String contestId);
}
