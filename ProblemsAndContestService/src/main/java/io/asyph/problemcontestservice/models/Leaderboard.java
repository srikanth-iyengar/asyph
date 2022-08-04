package io.asyph.problemcontestservice.models;

import java.util.List;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

@Table("leaderboard")
public class Leaderboard {

	@PrimaryKey
	private LeaderboardKey key;

	@Column("score")
	@CassandraType(type = Name.INT)
	private Integer score;

	@Column("problems_attempted_list")
	private List<String> problemsSolvedIds;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public List<String> getProblemsSolvedIds() {
		return problemsSolvedIds;
	}

	public void setProblemsSolvedIds(List<String> problemsSolvedIds) {
		this.problemsSolvedIds = problemsSolvedIds;
	}
}
