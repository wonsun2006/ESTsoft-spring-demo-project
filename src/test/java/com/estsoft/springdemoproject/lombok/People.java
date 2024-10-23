package com.estsoft.springdemoproject.lombok;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class People {
	private final Long id;
	private final String name;
	private int age;
	private List<String> hobbies;

	// public People(Long id, String name) {
	// 	this.id = id;
	// 	this.name = name;
	// }
}
