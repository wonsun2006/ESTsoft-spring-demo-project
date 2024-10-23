package com.estsoft.springdemoproject.interf;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class InterImplA implements Inter{
	@Override
	public void method() {
		System.out.println("Hi A!");
	}
}
