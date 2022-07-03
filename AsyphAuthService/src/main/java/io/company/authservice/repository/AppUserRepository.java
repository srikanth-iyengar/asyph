package io.company.authservice.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import io.company.authservice.models.AppUser;

public interface AppUserRepository extends CassandraRepository<AppUser, String> {
	AppUser findByUsername(String username);
	
	@AllowFiltering
	AppUser findByEmailId(String emailId);
}
