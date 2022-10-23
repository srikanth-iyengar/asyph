package io.srikanth.onlinejudge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.srikanth.onlinejudge.models.JudgeRequest;
import io.srikanth.onlinejudge.models.JudgeResponse;
import io.srikanth.onlinejudge.models.Verdict;
import io.srikanth.onlinejudge.service.CodeRunner;

@SpringBootTest
class OnlineJudgeApplicationTests {

	@Autowired
	private CodeRunner codeRunner;

	@Test
	void contextLoads() {
	}

	@Test
	void environmentCheck() {
		String OS = System.getProperty("os.name");
		assertEquals(OS, "Linux");
	}


	@Test
	@DisplayName("compileProgramCheck_Verdict_JudgeResponseQueued")
	void compileProgramCheck() throws IOException, InterruptedException {
		JudgeRequest req = new JudgeRequest();
		req.setContestId("contest_1571");
		req.setProblemId("A");
		req.setCompiler("JAVA17");
		req.setCode("import java.io.*; public class Main { public static void main(String args[]) throws Exception{ System.out.println(\"Hello World\"); Thread.sleep(800);}}");
		req.setTimeLimit(2.0);
		req.setTestCases(1);

		JudgeResponse res = codeRunner.initiateRequest(req);

		assertEquals(res.getVerdict(), Verdict.QUEUED);
	}
}
