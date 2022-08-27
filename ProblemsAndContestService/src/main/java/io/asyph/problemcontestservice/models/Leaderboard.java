package io.asyph.problemcontestservice.models;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("leaderboard")
public class Leaderboard {

    @PrimaryKey
    private LeaderboardKey key;

    @Column("score")
    @CassandraType(type = Name.INT)
    private Integer score;

    @Column("problems_attempted_list")
    private Map<String, ProblemCache> problemsSolved;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Map<String, ProblemCache> getproblemsSolved() {
        return problemsSolved;
    }

    public void setproblemsSolved(Map<String, ProblemCache> problemsSolved) {
        this.problemsSolved = problemsSolved;
    }

    public Leaderboard(LeaderboardKey key) {
        this.key = key;
        this.score = 0;
        this.problemsSolved = new HashMap<>();
    }
}
