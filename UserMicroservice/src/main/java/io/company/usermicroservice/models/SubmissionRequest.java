package io.company.usermicroservice.models;

import java.time.LocalDateTime;

public class SubmissionRequest {
	LocalDateTime submissionTime;
	String contestId;
	String problemId;
	Verdict verdict;
	Language language;
	String code;
	Problems problem;
}
