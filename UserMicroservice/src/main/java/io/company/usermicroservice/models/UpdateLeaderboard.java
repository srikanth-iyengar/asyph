package io.company.usermicroservice.models;

public class UpdateLeaderboard {
	public String username;
	public String problemId;
	public String contestId;
	public Boolean isAccepted;

	public UpdateLeaderboard(String username, String problemId, String contestId, boolean isAccepted) {
		this.username = username;
		this.problemId = problemId;
		this.contestId = contestId;
		this.isAccepted = isAccepted;
	}
}
