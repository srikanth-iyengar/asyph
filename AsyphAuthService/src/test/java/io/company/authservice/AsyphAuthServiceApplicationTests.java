package io.company.authservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AsyphAuthServiceApplicationTests {

	@Test
	void contextLoads() {
		System.out.print("Hello");
	}


	@Test
	void test1() {
		// fail("Not implemented yet");
		int a = 1000;
		int b = 10000;
		assertEquals(a+b, 11000, "Broken addition");
	}

}
