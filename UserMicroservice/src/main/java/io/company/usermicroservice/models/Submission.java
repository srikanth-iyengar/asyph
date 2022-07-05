package io.company.usermicroservice.models;

import java.time.LocalDateTime;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("submission")
public class Submission {

	@PrimaryKey
	private SubmissionPrimaryKey key;

	@Column("submission_time")
	@CassandraType(type = Name.TIMESTAMP)
	private LocalDateTime submissionTime;

	@Column("contest_id")
	@CassandraType(type = Name.TEXT)
	private String contestId;

	@Column("problem_id")
	@CassandraType(type = Name.TEXT)
	private String problemId;

	@Column("verdict")
	@CassandraType(type = Name.TEXT)
	private Verdict verdict;

	@Column("note")
	@CassandraType(type = Name.TEXT)
	private String note;

	@Column("execution_time")
	@CassandraType(type = Name.DECIMAL)
	private Double executionTime;

	@Column("memory")
	@CassandraType(type = Name.DECIMAL)
	private Double memory;

	@Column("language")
	@CassandraType(type = Name.TEXT)
	private Language language;

	public SubmissionPrimaryKey getKey() {
		return key;
	}

	public void setKey(SubmissionPrimaryKey key) {
		this.key = key;
	}

	public LocalDateTime getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(LocalDateTime submissionTime) {
		this.submissionTime = submissionTime;
	}

	public String getContestId() {
		return contestId;
	}

	public void setContestId(String contestId) {
		this.contestId = contestId;
	}

	public String getProblemId() {
		return problemId;
	}

	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}

	public Verdict getVerdict() {
		return verdict;
	}

	public void setVerdict(String verdict) {
		this.verdict = Verdict.valueOf(verdict);
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
