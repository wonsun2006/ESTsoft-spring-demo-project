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
public class CommentDTO {
	private Long commentId;
	private Long articleId;
	private String body;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime createdAt;

	public CommentDTO(Comment comment) {
		this.commentId = comment.getId();
		this.articleId = comment.getArticle().getId();
		this.body = comment.getBody();
		this.createdAt = comment.getCreatedAt();
	}
}