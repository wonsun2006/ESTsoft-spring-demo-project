package com.estsoft.springdemoproject.blog.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import com.estsoft.springdemoproject.blog.domain.dto.ArticleRequestDTO;
import com.estsoft.springdemoproject.blog.service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class BlogControllerTest2 {
	@InjectMocks
	BlogController blogController;

	@Mock
	BlogService blogService;

	MockMvc mockMvc;

	@BeforeEach
	public void setUp(){
		this.mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
	}

	@Test
	public void testSaveArticle() throws Exception {
		// given : API 호출에 필요한 데이터 만들기
		String title = "title";
		String content = "content";
		ArticleRequestDTO request = new ArticleRequestDTO(title,content);
		ObjectMapper objectMapper = new ObjectMapper();
		String articleJson = objectMapper.writeValueAsString(request);

		// stub (service.saveArticle 호출 시)
		Mockito.when(blogService.saveArticle(any())).thenReturn(new Article(title, content));

		// when : API 호출
		ResultActions resultActions = mockMvc.perform(
			post("/api/articles")
			.content(articleJson)
			.contentType(MediaType.APPLICATION_JSON)
		);

		// then : 호출 결과 검증
		resultActions.andExpect(status().isCreated())
			.andExpect(jsonPath("title").value(title))
			.andExpect(jsonPath("content").value(content));
	}

	@Test
	public void testDelete() throws Exception {
		// given : API 호출에 필요한 데이터 만들기
		Long id = 1L;

		// when : API 호출
		ResultActions resultActions = mockMvc.perform(delete("/api/articles/{id}", id));

		// then : 호출 결과 검증
		resultActions.andExpect(status().isOk());

		verify(blogService, times(1)).deleteById(id);
	}

	@Test
	public void testFindOne() throws Exception {
		// given : API 호출에 필요한 데이터 만들기
		Long id = 1L;

		Mockito.doReturn(new Article("title","content")).when(blogService).findById(id);

		// when : API 호출
		ResultActions resultActions = mockMvc.perform(get("/api/articles/{id}", id));

		// then : 호출 결과 검증
		resultActions.andExpect(status().isOk());

		verify(blogService, times(1)).findById(id);
	}
}