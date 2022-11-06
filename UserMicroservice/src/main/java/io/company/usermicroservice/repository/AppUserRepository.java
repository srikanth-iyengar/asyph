package io.company.usermicroservice.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import io.company.usermicroservice.models.AppUser;

public interface AppUserRepository extends CassandraRepository<AppUser, String> {
	
	@AllowFiltering
	AppUser findByUsername(String username);
	@AllowFiltering
	AppUser findByEmailId(String emaiId);
}
