package com.estsoft.springdemoproject.blog.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.estsoft.springdemoproject.blog.domain.Article;
import com.estsoft.springdemoproject.blog.domain.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleNestingCommentsDTO {
	private Long articleId;
	private String title;
	private String content;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime createdAt;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime updatedAt;
	private List<CommentDTO> comments;

	public ArticleNestingCommentsDTO(Article article, List<Comment> commentList) {
		articleId = article.getId();
		title = article.getTitle();
		content = article.getContent();
		createdAt = article.getCreatedAt();
		updatedAt = article.getUpdatedAt();
		comments = commentList.stream().map(CommentDTO::new).toList();
	}
}

