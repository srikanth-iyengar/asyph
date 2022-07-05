package io.asyph.problemcontestservice.models;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.oss.driver.api.core.uuid.Uuids;

@Table("problem")
public class Problems {

	@PrimaryKey
	@Column("problem_id")
	@CassandraType(type = Name.TEXT)
	private String problemId;

	@Column("problem_name")
	@CassandraType(type = Name.TEXT)
	private String problemName;

	@Column("contest_id")
	@CassandraType(type = Name.TEXT)
	private String contestId;

	@Column("test_cases")
	@CassandraType(type = Name.INT)
	private Integer testCases;

	@Column("time_limit")
	@CassandraType(type = Name.DECIMAL)
	private Double timeLimit;

	@Column("memory_limit")
	@CassandraType(type = Name.DECIMAL)
	private Double memoryLimit;

	@Column("problem_statement")
	@CassandraType(type = Name.TEXT)
	private String problemStatement;

	private Problems(Builder builder) {
		setContestId(contestId);
		setMemoryLimit(builder.memoryLimit);
		setProblemId();
		setProblemName(builder.problemName);
		setProblemStatement(builder.problemStatement);
		setTestCases(builder.testCases);
		setTimeLimit(builder.timeLimit);
	}

	static class Builder {
		String problemName, contestId, problemStatement;
		Integer testCases;
		Double timeLimit, memoryLimit;

		public Builder() {

		}

		public Builder problemId(String problemName) {
			this.problemName = problemName;
			return this;
		}

		public Builder contestId(String contestId) {
			this.contestId = contestId;
			return this;
		}

		public Builder problemStatement(String problemStatement) {
			this.problemStatement = problemStatement;
			return this;
		}

		public Builder testCases(Integer testCases) {
			this.testCases = testCases;
			return this;
		}

		public Builder timeLimit(Double timeLimit) {
			this.timeLimit = timeLimit;
			return this;
		}

		public Builder memoryLimit(Double memoryLimit) {
			this.memoryLimit = memoryLimit;
			return this;
		}
	}

	public String getProblemId() {
		return problemId;
	}

	public void setProblemId() {
		this.problemId = Uuids.timeBased().toString();
	}

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public String getContestId() {
		return contestId;
	}

	public void setContestId(String contestId) {
		this.contestId = contestId;
	}

	public Integer getTestCases() {
		return testCases;
	}

	public void setTestCases(Integer testCases) {
		this.testCases = testCases;
	}

	public Double getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Double timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Double getMemoryLimit() {
		return memoryLimit;
	}

	public void setMemoryLimit(Double memoryLimit) {
		this.memoryLimit = memoryLimit;
	}

	public String getProblemStatement() {
		return problemStatement;
	}

	public void setProblemStatement(String problemStatement) {
		this.problemStatement = problemStatement;
	}
}
