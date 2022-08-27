package io.asyph.problemcontestservice.models;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class LeaderboardKey {


	@PrimaryKeyColumn(name = "contest_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String contestId;

	@PrimaryKeyColumn(name = "username", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String username;

	public String getcontestId() {
		return contestId;
	}

	public void setcontestId(String contestId) {
		this.contestId = contestId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LeaderboardKey(String contestId, String username) {
		this.contestId = contestId;
		this.username = username;
	}
}
