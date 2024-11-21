package com.estsoft.springdemoproject.blog.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.estsoft.springdemoproject.blog.domain.Article;
import com.estsoft.springdemoproject.blog.domain.Comment;
import com.estsoft.springdemoproject.blog.domain.dto.ArticleNestingCommentsDTO;
import com.estsoft.springdemoproject.blog.domain.dto.CommentRequestDTO;
import com.estsoft.springdemoproject.blog.repository.BlogRepository;
import com.estsoft.springdemoproject.blog.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class CommentControllerTest {
	@InjectMocks
	CommentController commentController;

	@Mock
	CommentService commentService;

	@Mock
	BlogRepository blogRepository;

	MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
	}

	@Test
	void saveCommentByArticleId() throws Exception {
		// given
		Article article = getArticle();
		String body = "body";
		CommentRequestDTO request = new CommentRequestDTO(body);
		ObjectMapper objectMapper = new ObjectMapper();
		String commentJson = objectMapper.writeValueAsString(request);

		// stub
		Mockito.when(commentService.saveComment(any(), any())).thenReturn(new Comment(body, article));

		// when
		ResultActions resultActions = mockMvc.perform(
			post("/api/articles/{articleId}/comments", 1L)
				.content(commentJson)
				.contentType(MediaType.APPLICATION_JSON)
		);

		// then
		resultActions.andExpect(status().isCreated())
			.andExpect(jsonPath("$.body").value(body));
	}

	@Test
	void getComment() throws Exception {
		// given
		Article article = getArticle();
		String body = "body";

		// stub
		Mockito.when(commentService.findComment(any())).thenReturn(new Comment(body, article));

		// when
		ResultActions resultActions = mockMvc.perform(get("/api/comments/{commentId}", 1L));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$.body").value(body));
	}

	@Test
	void editComment() throws Exception {
		// given
		Article article = getArticle();
		String body = "body";
		CommentRequestDTO request = new CommentRequestDTO(body);
		ObjectMapper objectMapper = new ObjectMapper();
		String commentJson = objectMapper.writeValueAsString(request);

		// stub
		Mockito.when(commentService.updateComment(any(), any())).thenReturn(new Comment(body, article));

		// when
		ResultActions resultActions = mockMvc.perform(
			put("/api/comments/{commentId}", 1L)
				.content(commentJson)
				.contentType(MediaType.APPLICATION_JSON)
		);

		// then
		resultActions.andExpect(status().isCreated())
			.andExpect(jsonPath("$.body").value(body));
	}

	@Test
	void removeComment() throws Exception {
		// given

		// when
		ResultActions resultActions = mockMvc.perform(delete("/api/comments/{commentId}", 1L));

		// then
		resultActions.andExpect(status().isOk());
	}

	@Test
	void getArticleWithCommentList() throws Exception {
		// given
		Article article = getArticle();
		List<Comment> commentList = List.of(new Comment("body1", article), new Comment("body2", article));

		Mockito.when(commentService.getArticleNestingComments(any()))
			.thenReturn(new ArticleNestingCommentsDTO(article, commentList));

		// when
		ResultActions resultActions = mockMvc.perform(get("/api/articles/{articleId}/comments", 1L));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$.title").value(article.getTitle()))
			.andExpect(jsonPath("$.content").value(article.getContent()))
			.andExpect(jsonPath("$.comments[0].body").value(commentList.get(0).getBody()))
			.andExpect(jsonPath("$.comments[1].body").value(commentList.get(1).getBody()));

	}

	private static Article getArticle() {
		return new Article("title", "content");
	}
}