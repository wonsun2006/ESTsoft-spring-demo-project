package com.estsoft.springdemoproject.blog.domain.dto;

import java.time.LocalDateTime;

import com.estsoft.springdemoproject.blog.domain.Article;
import com.estsoft.springdemoproject.blog.domain.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentResponseDTO {
	private Long commentId;
	private Long articleId;
	private String body;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime createdAt;
	private ArticleDTO article;

	public CommentResponseDTO(Comment comment){
		Article articleFromComment = comment.getArticle();

		commentId = comment.getId();
		articleId = articleFromComment.getId();
		body = comment.getBody();
		createdAt = comment.getCreatedAt();
		article = new ArticleDTO(articleFromComment);
	}
}
