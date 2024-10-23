package com.estsoft.springdemoproject.controller;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	// Person  /thymeleaf
	@GetMapping("/thymeleaf/example")
	public String show(Model model){
		Person person = new Person();
		person.setId(1L);
		person.setName("김자바");
		person.setAge(20);
		person.setHobbies(Arrays.asList("달리기", "줄넘기", "복싱"));

		model.addAttribute("person", person);
		model.addAttribute("today", LocalDateTime.now());

		return "examplePage";
	}
}
