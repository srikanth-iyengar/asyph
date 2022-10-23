package io.company.usermicroservice.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import io.company.usermicroservice.models.AppUser;

public interface AppUserRepository extends CassandraRepository<AppUser, String> {
	AppUser findByUsername(String username);
}
