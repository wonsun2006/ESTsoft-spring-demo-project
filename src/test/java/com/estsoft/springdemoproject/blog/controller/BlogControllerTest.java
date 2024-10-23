package com.estsoft.springdemoproject.blog.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.estsoft.springdemoproject.blog.domain.Article;
import com.estsoft.springdemoproject.blog.domain.dto.UpdateArticleRequestDTO;
import com.estsoft.springdemoproject.blog.repository.BlogRepository;
import com.estsoft.springdemoproject.blog.service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class BlogControllerTest {
	@Autowired
	private WebApplicationContext context;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private BlogRepository repository;
	@Autowired
	private BlogService blogService;

	@BeforeEach
	public void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	// POST /articles API 테스트
	@Test
	public void addArticle() throws Exception {
		// given: article 저장
		Article article = new Article("제목", "내용");
		// 직렬화
		String json = objectMapper.writeValueAsString(article);

		// when: POST /articles API 호출
		ResultActions resultActions = mockMvc.perform(post("/articles")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json));

		// then: 호출 결과 검증
		resultActions.andExpect(status().isCreated())
			.andExpect(jsonPath("$.title").value(article.getTitle()))
			.andExpect(jsonPath("$.content").value(article.getContent()));
		List<Article> articleList = repository.findAll();
		Assertions.assertThat(articleList.size()).isEqualTo(1);
	}

	// 블로그 게시글 조회 API
	@Test
	public void findAll() throws Exception {
		// given: 조회 API에 필요한 값 세팅
		Article article = repository.save(new Article("title", "content"));

		// when: 조회 API
		ResultActions resultActions = mockMvc.perform(get("/articles")
			.accept(MediaType.APPLICATION_JSON));

		// then: API 호출 결과 검증
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].title").value(article.getTitle()))
			.andExpect(jsonPath("$[0].content").value(article.getContent()));
	}

	@Test
	public void findOne() throws Exception {
		// given: data insert
		Article article = repository.save(new Article("title", "content"));
		Long id = article.getId();

		// when: API 호출
		ResultActions resultActions = mockMvc.perform(get("/articles/{id}", id)
			.accept(MediaType.APPLICATION_JSON));

		// then: API 호출 결과 검증
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(article.getId()))
			.andExpect(jsonPath("$.title").value(article.getTitle()))
			.andExpect(jsonPath("$.content").value(article.getContent()));
	}

	@Test
	public void findOneException() throws Exception {
		// when
		ResultActions resultActions = mockMvc.perform(get("/articles/{id}", 1000L)
			.accept(MediaType.APPLICATION_JSON));

		// given
		resultActions.andExpect(status().isBadRequest());
	}

	@Test
	public void deleteTest() throws Exception {
		// given: data insert
		Article article = repository.save(new Article("title", "content"));
		Long id = article.getId();

		// when: API 호출
		ResultActions resultActions = mockMvc.perform(delete("/articles/{id}", id)
			.accept(MediaType.APPLICATION_JSON));

		// then: API 호출 결과 검증
		resultActions.andExpect(status().isOk());
	}

	@Test
	public void updateArticleTest() throws Exception {
		Article article = repository.save(new Article("title", "content"));
		Long id = article.getId();

		// 수정 데이터(object) -> json
		UpdateArticleRequestDTO request = new UpdateArticleRequestDTO("updated title", "updated content");
		String updateJsonContent = objectMapper.writeValueAsString(request);

		ResultActions resultActions = mockMvc.perform(put("/articles/{id}",id)
			.contentType(MediaType.APPLICATION_JSON)
			.content(updateJsonContent));

		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$.title").value(request.getTitle()))
			.andExpect(jsonPath("$.content").value(request.getContent()));
	}

	@Test
	public void updateArticleFail() throws Exception{
		Long notExistsId = 1000L;
		UpdateArticleRequestDTO request = new UpdateArticleRequestDTO("title", "content");
		String updateJsonContent = objectMapper.writeValueAsString(request);

		ResultActions resultActions = mockMvc.perform(put("/articles/{id}", notExistsId)
			.contentType(MediaType.APPLICATION_JSON)
			.content(updateJsonContent));

		resultActions.andExpect(status().isBadRequest());
		assertThrows(IllegalArgumentException.class, () -> blogService.findById(notExistsId));
	}
}