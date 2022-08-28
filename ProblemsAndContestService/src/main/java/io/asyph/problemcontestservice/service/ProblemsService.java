package io.asyph.problemcontestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import io.asyph.problemcontestservice.models.CreateProblem;
import io.asyph.problemcontestservice.models.Problems;
import io.asyph.problemcontestservice.models.UpdateProblem;
import io.asyph.problemcontestservice.repository.ProblemRepository;

@Service
public class ProblemsService {

	@Autowired
	private ProblemRepository problemRepository;

	@Autowired
	private WebClient.Builder webClientBuilder;


	public Problems getProblem(String problemId) {
		return problemRepository.findById(problemId).get();
	}

	public Problems createProblem(CreateProblem request) {
		Problems problem = new Problems.Builder(request.contestId, request.problemName).build();
		System.out.println(request.contestId);
		problemRepository.save(problem);
		return problem;
	}

	public Problems updateProblem(UpdateProblem request ) {
		Problems problem = problemRepository.findByProblemId(request.problemId).get();
		problem.setTimeLimit(request.timeLimit);
		problem.setTestCases(request.testCases);
		problem.setProblemStatement(request.problemStatement);
		problem.setMemoryLimit(request.memoryLimit);
		problem.setProblemName(request.problemName);
		problem.setScore(request.score);
		problemRepository.save(problem);
		return problem;
	}

	public void deleteProblem(String problemId, String contestId){
		problemRepository.deleteById(problemId);
		deleteTestCases(problemId, contestId);
	}

	public String saveTestCases(MultipartFile file, String problemId, String contestId) {
		MultipartBodyBuilder builder = new MultipartBodyBuilder();
		builder.part("file", file.getResource());
		String status = webClientBuilder.build()
			.post()
			.uri("http://ONLINE-JUDGE/upload-test-cases?problemId=" + problemId + "&contestId=" + contestId)
			.contentType(MediaType.MULTIPART_FORM_DATA)
			.body(BodyInserters.fromMultipartData(builder.build()))
			.retrieve()
			.bodyToMono(String.class)
			.block();
		return status;
	}

	public void deleteTestCases(String problemId, String contestId) {
		webClientBuilder.build()
			.delete()
			.uri("http://ONLINE-JUDGE/delete-test-cases?problemId=" + problemId + "&contestId=" + contestId)
			.retrieve()
			.bodyToMono(String.class)
			.block();
	}
}
