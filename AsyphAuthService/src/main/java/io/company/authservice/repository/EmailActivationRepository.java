package io.company.authservice.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import io.company.authservice.models.EmailActivation;

public interface EmailActivationRepository extends CassandraRepository<EmailActivation, String> {
	
	@AllowFiltering
	EmailActivation findByUsername(String username);

	
}
