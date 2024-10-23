package com.estsoft.springdemoproject.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.estsoft.springdemoproject.user.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {
	private final UserRepository userRepository;

	public UserDetailService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		return userRepository.findByEmail(username).orElseThrow(() -> new IllegalStateException(username));
	}
}
