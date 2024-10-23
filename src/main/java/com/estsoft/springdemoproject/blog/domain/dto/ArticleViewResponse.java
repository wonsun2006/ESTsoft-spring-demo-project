package com.estsoft.springdemoproject.blog.domain.dto;

import java.time.LocalDateTime;

import com.estsoft.springdemoproject.blog.domain.Article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// view 구성을 위한 DTO
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleViewResponse {
	private Long id;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public ArticleViewResponse(Article article) {
		this.id = article.getId();
		this.title = article.getTitle();
		this.content = article.getContent();
		this.createdAt = article.getCreatedAt();
		this.updatedAt = article.getUpdatedAt();
	}
}
