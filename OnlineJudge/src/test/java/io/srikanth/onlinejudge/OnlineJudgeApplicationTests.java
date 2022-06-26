package io.srikanth.onlinejudge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnlineJudgeApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void environmentCheck() {
		String OS = System.getProperty("os.name");
		assertEquals(OS, "Linux");
	}
}
