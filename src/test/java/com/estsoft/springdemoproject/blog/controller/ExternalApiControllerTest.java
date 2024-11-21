package com.estsoft.springdemoproject.blog.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.estsoft.springdemoproject.blog.service.BlogService;
import com.estsoft.springdemoproject.blog.service.CommentService;

@ExtendWith(MockitoExtension.class)
class ExternalApiControllerTest {
	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private ExternalApiController externalApiController;

	@Mock
	private BlogService blogService;

	@Mock
	private CommentService commentService;

	MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(externalApiController).build();
	}

	@Test
	void fetchExternalPosts() throws Exception {
		// given

		// when
		ResultActions resultActions = mockMvc.perform(get("/api/external/articles"));

		// then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$").value("OK"));
	}

	@Test
	void fetchExternalComments() throws Exception {
		// given

		// when
		ResultActions resultActions = mockMvc.perform(get("/api/external/comments"));

		// then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$").value("OK"));
	}
}