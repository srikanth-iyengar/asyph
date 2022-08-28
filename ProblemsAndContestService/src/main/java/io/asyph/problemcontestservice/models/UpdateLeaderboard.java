package io.asyph.problemcontestservice.models;

public class UpdateLeaderboard {
	public String username;
	public String problemId;
	public String contestId;
	public Boolean isAccepted;
	

	public String toString() {
		return "{" + username + "," + problemId + "," + contestId + "," + isAccepted + "}";
	}
}
