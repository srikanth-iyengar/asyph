package io.asyph.problemcontestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.asyph.problemcontestservice.repository.LeaderboardRepository;

@Service
public class LeaderboardService {
	@Autowired
	private LeaderboardRepository	leaderboardRepository;
	
	public void updateLeaderboard() {
		/*
		 * Things that I want to do the update and recalculate the score
		 * 1. Contest Id
		 * 2. Problem Id
		 * 3. Accepted Or Not
		 * 4. Username
		 */
	}
}
