package io.asyph.problemcontestservice.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

import io.asyph.problemcontestservice.models.Leaderboard;
import io.asyph.problemcontestservice.models.LeaderboardKey;

public interface LeaderboardRepository extends CassandraRepository<Leaderboard, LeaderboardKey> {
	List<Leaderboard> findByKeyContestId(String contestId);
}
