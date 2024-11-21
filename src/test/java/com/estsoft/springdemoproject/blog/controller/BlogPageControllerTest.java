package com.estsoft.springdemoproject.blog.controller;

import static org.hamcrest.Matchers.*;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.estsoft.springdemoproject.blog.domain.Article;
import com.estsoft.springdemoproject.blog.domain.dto.ArticleViewResponse;
import com.estsoft.springdemoproject.blog.service.BlogService;

@ExtendWith(MockitoExtension.class)
class BlogPageControllerTest {
	@InjectMocks
	private BlogPageController blogPageController;

	@Mock
	private BlogService blogService;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(blogPageController).build();
	}

	@Test
	void showArticle() throws Exception {
		// given
		List<Article> articles = List.of(new Article("title1", "content1"), new Article("title2", "content2"));
		List<ArticleViewResponse> responses = articles.stream().map(ArticleViewResponse::new).toList();

		// stub
		Mockito.when(blogService.findAll()).thenReturn(articles);

		// when & then
		mockMvc.perform(get("/articles"))
			.andExpect(status().isOk())
			.andExpect(view().name("articleList"))
			.andExpect(model().attributeExists("articles"))
			.andExpect(model().attribute("articles", hasSize(2)))
			.andExpect(model().attribute("articles", hasItem(
				allOf(
					hasProperty("title", is("title1")),
					hasProperty("content", is("content1"))
				)
			)))
			.andExpect(model().attribute("articles", hasItem(
				allOf(
					hasProperty("title", is("title2")),
					hasProperty("content", is("content2"))
				)
			)));
	}

	@Test
	void showDetails() throws Exception {
		// given
		Article article = new Article("title", "content");
		ArticleViewResponse response = new ArticleViewResponse(article);

		// stub
		Mockito.when(blogService.findById(1L)).thenReturn(article);

		// when & then
		mockMvc.perform(get("/articles/{id}", 1L))
			.andExpect(status().isOk())
			.andExpect(view().name("article"))
			.andExpect(model().attributeExists("article"))
			.andExpect(model().attribute("article",
				allOf(
					hasProperty("title", is("title")),
					hasProperty("content", is("content"))
				)
			));
	}

	@Test
	void addArticle() throws Exception {
		// when & then
		mockMvc.perform(get("/new-article"))
			.andExpect(status().isOk())
			.andExpect(view().name("newArticle"))
			.andExpect(model().attributeExists("article"));
	}
}