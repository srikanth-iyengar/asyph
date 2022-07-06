package io.asyph.problemcontestservice.models;

import java.time.LocalDateTime;

public class RescheduleContestRequest {
	public String id;
	public LocalDateTime startTime;
	public LocalDateTime endTime;
}
