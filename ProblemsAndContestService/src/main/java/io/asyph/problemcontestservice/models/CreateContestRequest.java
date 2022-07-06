package io.asyph.problemcontestservice.models;

import java.time.LocalDateTime;

public class CreateContestRequest {
	public String username;
	public String contestName;
	public LocalDateTime startTime;
	public LocalDateTime endTime;
}
