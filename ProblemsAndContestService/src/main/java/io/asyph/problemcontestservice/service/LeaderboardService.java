package io.asyph.problemcontestservice.service;

import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.asyph.problemcontestservice.models.Leaderboard;
import io.asyph.problemcontestservice.models.LeaderboardKey;
import io.asyph.problemcontestservice.models.ProblemCache;
import io.asyph.problemcontestservice.models.Problems;
import io.asyph.problemcontestservice.models.UpdateLeaderboard;
import io.asyph.problemcontestservice.repository.LeaderboardRepository;
import io.asyph.problemcontestservice.repository.ProblemRepository;

@Service
public class LeaderboardService {
	@Autowired
	private LeaderboardRepository	leaderboardRepository;

	@Autowired
	private ProblemRepository problemRepository;

	final Integer PENALTY = -10;

	final Logger logger = LogManager.getLogger(LeaderboardService.class);

	public void updateLeaderboard(UpdateLeaderboard request) {
		/*
		 * Things that I want to do the update and recalculate the score
		 * 1. Contest Id
		 * 2. Problem Id
		 * 3. Accepted Or Not
		 * 4. Username
		 */
		logger.info(request.toString());
		LeaderboardKey key = new LeaderboardKey(request.contestId, request.username);
		Optional<Leaderboard> entry = leaderboardRepository.findById(key);
		Leaderboard rankList;
		logger.info(entry.isEmpty());
		if(entry.isEmpty()) {
			rankList = new Leaderboard(key);
		}
		else {
			rankList = entry.get();
		}
		ProblemCache curSubmissionProblem = rankList.getproblemsSolved().get(request.problemId);
		if(curSubmissionProblem == null) {
			curSubmissionProblem = new ProblemCache();
			Problems problem = problemRepository.findByProblemId(request.problemId).get();
			curSubmissionProblem.setPoints(problem.getScore());
			rankList.getproblemsSolved().put(request.problemId, curSubmissionProblem);
		}
		if(!curSubmissionProblem.getIsAccepted() && request.isAccepted) {
			rankList.setScore(rankList.getScore() + curSubmissionProblem.getPoints() + PENALTY * curSubmissionProblem.getWrongSubmission());
		}
		if(request.isAccepted) {
			curSubmissionProblem.setIsAccepted(true);
		}
		else {
			curSubmissionProblem.setWrongSubmission(curSubmissionProblem.getWrongSubmission() + 1);
		}
		leaderboardRepository.save(rankList);
		logger.info("Database Updated");
	}
}
