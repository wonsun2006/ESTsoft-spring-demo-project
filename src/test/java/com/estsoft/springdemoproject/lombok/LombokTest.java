package com.estsoft.springdemoproject.lombok;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class LombokTest {
	@Test
	public void test(){
		People people = new People(1L, "name", 20, Arrays.asList("달리기", "등등"));
		people.setHobbies(Arrays.asList("그외 취미","..."));
		System.out.println(people.getHobbies());

		People people2 = new People(2L,"estsoft");
		System.out.println(people.getName());
	}
}
