package com.estsoft.springdemoproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.springdemoproject.repository.Member;
import com.estsoft.springdemoproject.service.MemberService;

@RestController
public class MemberController {
	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/members")
	public List<Member> getAllMembers() {
		return memberService.getAllMembers();
	}

	@PostMapping("/members")
	public Member saveMember(@RequestBody Member member){
		return memberService.saveMember(member);
	}
}
