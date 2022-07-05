package io.asyph.problemcontestservice.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.asyph.problemcontestservice.models.Contest;
import io.asyph.problemcontestservice.models.CreateContestRequest;
import io.asyph.problemcontestservice.models.RescheduleContestRequest;
import io.asyph.problemcontestservice.repository.ContestRepository;

@Service
public class ContestService {

	@Autowired
	private ContestRepository contestRepository;

	@SuppressWarnings("unused")
	private final Logger logger = LogManager.getLogger(ContestService.class);

	public String createContest(CreateContestRequest request) {
		if (!canCreate(request.contestName)) {
			return "NA";
		}
		Contest contest = new Contest.Builder(request.username).contestName(request.contestName).endTime(request.endTime)
				.startTime(request.startTime).build();
		contestRepository.save(contest);
		return contest.getContestId();
	}

	public void deleteContest(String id) {
		Contest contest = contestRepository.findByContestId(id).get();
		contestRepository.delete(contest);
	}

	public Boolean canCreate(String request) {
		Optional<Contest> contest = contestRepository.findByContestName(request);
		return contest.isEmpty();
	}

	public Contest toggleUnlisting(String request) {
		Contest contest = contestRepository.findByContestId(request).get();
		contest.setUnlisted(!contest.getUnlisted());
		contestRepository.save(contest);
		return contest;
	}
	
	
	public List<Contest> getAllContests(String username) {
		List<Contest> contests = contestRepository.findByCreatedBy(username);
		return contests;
	}
	
	public Contest rescheduleContest(RescheduleContestRequest request) {
		Contest contest = contestRepository.findByContestId(request.id).get();
		contest.setStartTime(request.startTime);
		contest.setEndTime(request.endTime);
		contestRepository.save(contest);
		return contest;
	}
}
