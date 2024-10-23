package com.estsoft.springdemoproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estsoft.springdemoproject.repository.Member;
import com.estsoft.springdemoproject.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository repository;

	public List<Member> getAllMembers() {
		return repository.findAll();
	}

	public Member saveMember(Member member){
		return repository.save(member);
	}
}
