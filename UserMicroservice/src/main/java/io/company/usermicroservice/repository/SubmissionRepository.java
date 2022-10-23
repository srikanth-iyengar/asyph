package io.company.usermicroservice.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import io.company.usermicroservice.models.Submission;
import io.company.usermicroservice.models.SubmissionPrimaryKey;

public interface SubmissionRepository extends CassandraRepository<Submission, SubmissionPrimaryKey> {
	@AllowFiltering
	List<Submission> findByKeyUsername(String username);

	@AllowFiltering
	List<Submission> findByKeyUsernameAndContestId(String key, String contestId);

	@AllowFiltering
	Submission findByKeyId(String id);
}
