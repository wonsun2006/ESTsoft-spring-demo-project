package com.estsoft.springdemoproject.user.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.estsoft.springdemoproject.user.domain.Users;
import com.estsoft.springdemoproject.user.domain.dto.AddUserRequest;
import com.estsoft.springdemoproject.user.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;

	public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
		this.userRepository = userRepository;
		this.encoder = encoder;
	}

	// 회원가입
	public Users save(AddUserRequest dto){
		String password = dto.getPassword();
		String email = dto.getEmail();
		String encodedPassword = encoder.encode(password);
		Users users = new Users(email, encodedPassword);

		return userRepository.save(users);
	}
}
