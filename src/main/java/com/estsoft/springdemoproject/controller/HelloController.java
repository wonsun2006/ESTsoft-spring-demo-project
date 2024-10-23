package com.estsoft.springdemoproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.springdemoproject.interf.InterDependencyService;
import com.estsoft.springdemoproject.service.HelloService;

@RestController
public class HelloController {
	private final HelloService service;
	private final InterDependencyService dependencyService;

	HelloController(HelloService service, InterDependencyService dependencyService){
		this.service=service;
		this.dependencyService = dependencyService;
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value="param", defaultValue = "Spring") String param){
		dependencyService.printMethod();
		return service.printHello(param);
	}
}