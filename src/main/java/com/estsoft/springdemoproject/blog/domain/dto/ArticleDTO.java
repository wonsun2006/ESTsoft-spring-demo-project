package com.estsoft.springdemoproject.blog.domain.dto;

import java.time.LocalDateTime;

import com.estsoft.springdemoproject.blog.domain.Article;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "블로그 조회 결과")
public class ArticleDTO {
	@Schema(description = "블로그 ID", type = "Long")
	private Long articleId;
	@Schema(description = "블로그 제목", type = "String")
	private String title;
	@Schema(description = "블로그 내용", type = "String")
	private String content;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime createdAt;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime updatedAt;

	public ArticleDTO(Article article) {
		articleId = article.getId();
		title = article.getTitle();
		content = article.getContent();
		createdAt = article.getCreatedAt();
		updatedAt = article.getUpdatedAt();
	}
}
