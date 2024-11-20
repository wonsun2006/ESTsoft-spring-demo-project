package com.estsoft.springdemoproject.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddUserRequest {
	private String email;
	private String password;
}
