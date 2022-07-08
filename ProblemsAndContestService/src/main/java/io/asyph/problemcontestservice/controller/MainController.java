package io.asyph.problemcontestservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.asyph.problemcontestservice.models.Contest;
import io.asyph.problemcontestservice.models.CreateContestRequest;
import io.asyph.problemcontestservice.models.CreateProblem;
import io.asyph.problemcontestservice.models.Problems;
import io.asyph.problemcontestservice.models.RescheduleContestRequest;
import io.asyph.problemcontestservice.service.ContestService;
import io.asyph.problemcontestservice.service.ProblemsService;

@RestController
public class MainController {
	@Autowired
	private ContestService contestService;

	@Autowired
	private ProblemsService problemsService;

	@PostMapping("/create-contest")
	public String createContest(@RequestBody CreateContestRequest request) {
		return contestService.createContest(request);
	}

	@DeleteMapping("/delete-contest")
	public String deleteContest(@RequestParam String id) {
		contestService.deleteContest(id);
		return "Contest deleted successfully";
	}

	@PutMapping("/toggle-unlist")
	public Contest toggleUnlisting(@RequestParam String id) {
		return contestService.toggleUnlisting(id);
	}

	@GetMapping("/get-contests/{username}")
	public List<Contest> getContest(@PathVariable String username) {
		return contestService.getAllContests(username);
	}


	@PutMapping("/reschedule-contest")
	public Contest rescheduleContest(@RequestBody RescheduleContestRequest request) {
		return contestService.rescheduleContest(request);
	}

	@PostMapping("/add-new-problem")
	public Problems addNewProblem(@RequestBody CreateProblem request) {
		return problemsService.createProblem(request);
	}


	@PostMapping("/upload-test-cases")
	public String uploadTestCases(@RequestParam("file") MultipartFile file, @RequestParam String problemId, @RequestParam String contestId) {
		String status = problemsService.saveTestCases(file, problemId, contestId);
		return status;
	}

	@DeleteMapping("delete-problem")
	public void deleteProblem(@RequestParam String problemId, @RequestParam String contestId) {
		problemsService.deleteProblem(problemId, contestId);
	}

	@DeleteMapping("/delete-test-cases")
	public void deleteTestCase(@RequestParam String problemId, @RequestParam String contestId) {
		problemsService.deleteTestCases(problemId, contestId);
	}
}
