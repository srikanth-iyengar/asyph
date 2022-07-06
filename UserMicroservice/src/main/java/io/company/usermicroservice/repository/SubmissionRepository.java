package io.company.usermicroservice.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import io.company.usermicroservice.models.Submission;
import io.company.usermicroservice.models.SubmissionPrimaryKey;

public interface SubmissionRepository extends CassandraRepository<Submission, SubmissionPrimaryKey> {
	
}
