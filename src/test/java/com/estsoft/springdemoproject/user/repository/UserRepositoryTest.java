package com.estsoft.springdemoproject.user.repository;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.estsoft.springdemoproject.user.domain.Users;

@DataJpaTest
class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;

	// 사용자 이메일로 조회 기능 검증 테스트
	@Test
	public void testFindByEmail(){
		// given : when 절에서 조회할 사용자 정보 저장
		Users user = getUsers();
		userRepository.save(user);

		// when
		Users returnUser = userRepository.findByEmail(user.getEmail()).orElseThrow();

		// then
		assertThat(returnUser.getEmail(), is(user.getEmail()));
		assertThat(returnUser.getPassword(), is(user.getPassword()));
	}

	// 사용자 정보 저장 기능
	@Test
	public void testSave(){
		// given
		Users user = getUsers();

		// when
		Users saved = userRepository.save(user);

		// then
		assertThat(saved.getEmail(), is(user.getEmail()));
	}

	// 사용자 전체 조회 기능
	@Test
	public void testFindAllUser(){
		// given : when 절에서 조회할 사용자 정보 저장
		List<Users> userList = List.of(
			new Users("test1@test.com", "pw1"),
			new Users("test2@test.com", "pw2")
		);

		userRepository.saveAll(userList);

		// when
		List<Users> savedUserList = userRepository.findAll();

		// then
		assertThat(savedUserList.size(), is(userList.size()));
		for(int i=0;i<savedUserList.size();i++){
			assertThat(savedUserList.get(i).getEmail(),is(userList.get(i).getEmail()));
			assertThat(savedUserList.get(i).getPassword(),is(userList.get(i).getPassword()));
		}
	}

	private static Users getUsers() {
		String email = "test@test.com";
		String password = "pw1234567";
		return new Users(email,password);
	}
}