package io.company.usermicroservice.models;



public class JudgeRequest extends Request{
	private String contestId;
	private String problemId;
	private int testCases;
	private double timeLimit;

	public String getProblemId() {
		return problemId;
	}

	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}

	public String getContestId() {
		return contestId;
	}

	public void setContestId(String contestId) {
		this.contestId = contestId;
	}

	public int getTestCases() {
		return testCases;
	}

	public void setTestCases(int testCases) {
		this.testCases = testCases;
	}

	public double getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(double timeLimit) {
		this.timeLimit = timeLimit;
	}

	@Override
	public String toString() {
		return "RunRequest [code=" + code + ", compiler=" + compiler + ", contest_id=" + contestId + ", problem_index="
				+ problemId + ", testCases=" + testCases + ", timeLimit=" + timeLimit + "]";
	}

}
