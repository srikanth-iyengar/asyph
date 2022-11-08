package io.company.usermicroservice.repository;

import java.util.List;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import io.company.usermicroservice.models.Blog;

public interface BlogRepository extends CassandraRepository<Blog, String> {
	@AllowFiltering
	List<Blog> findByOrderByUpdatedAtAsc(Pageable pageable);
}
