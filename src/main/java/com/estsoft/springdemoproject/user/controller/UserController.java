package com.estsoft.springdemoproject.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.estsoft.springdemoproject.user.domain.dto.AddUserRequest;
import com.estsoft.springdemoproject.user.service.UserService;

@Controller
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	// POST /user 요청 받고 회원가입 처리
	@PostMapping("/user")
	public String save(@ModelAttribute AddUserRequest request){
		userService.save(request);
		return "redirect:/login";
	}
}
