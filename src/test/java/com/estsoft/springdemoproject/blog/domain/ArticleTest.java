package com.estsoft.springdemoproject.blog.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArticleTest {
	@Test
	public void test(){
		Article article = new Article("제목","내용");

		Article articleBuilder = Article.builder()
			.title("제목2")
			.content("내용2")
			.build();
	}
}