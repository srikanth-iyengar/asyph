package io.company.usermicroservice.models;

import com.datastax.oss.driver.api.core.uuid.Uuids;

public class Problems {

	private String problemId;

	private String problemName;

	private String contestId;

	private Integer testCases;

	private Double timeLimit;

	private Double memoryLimit;

	private String problemStatement;


	public Problems() {

	}

	public String getProblemId() {
		return problemId;
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
