package com.estsoft.springdemoproject;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComponentScanTest {

	@Test
	public void test(){
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringDemoProjectApplication.class);
		String[] beanNames = context.getBeanDefinitionNames();
		for(String bean:beanNames){
			System.out.println("#bean name: "+bean);
		}
		System.out.println("run test code");
	}
}
