package com.estsoft.springdemoproject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JUnitTest {
	@Test
	public void test(){
		// given
		int a = 1;
		int b = 2;

		// when
		int sum = a + b;

		// then
		Assertions.assertEquals(sum, 3);
	}
}
