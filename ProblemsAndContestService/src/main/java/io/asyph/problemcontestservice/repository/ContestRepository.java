package io.asyph.problemcontestservice.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import io.asyph.problemcontestservice.models.Contest;

public interface ContestRepository extends CassandraRepository<Contest, String> {
	Optional<Contest> findByContestId(String contestId);

	@AllowFiltering
	Optional<Contest> findByStartTime(LocalDateTime startTime);

	@AllowFiltering
	Optional<Contest> findByContestName(String contestName);

	@AllowFiltering
	List<Contest> findByCreatedBy(String createdBy);

}
