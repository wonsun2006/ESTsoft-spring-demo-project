package com.estsoft.springdemoproject.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostContent {
	Long id;
	String title;
	String body;

	@Override
	public String toString(){
		return "#title: " + title + " body: " + body + "\n";
	}
}
