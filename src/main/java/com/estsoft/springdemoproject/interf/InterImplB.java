package com.estsoft.springdemoproject.interf;

import org.springframework.stereotype.Component;

@Component
public class InterImplB implements Inter{
	@Override
	public void method() {
		System.out.println("Hi B!");
	}
}

