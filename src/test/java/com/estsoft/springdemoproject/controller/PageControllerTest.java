package com.estsoft.springdemoproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class PageControllerTest {
	@InjectMocks
	PageController pageController;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(pageController).build();
	}

	@Test
	void show() throws Exception {
		mockMvc.perform(get("/thymeleaf/example"))
			.andExpect(status().isOk())
			.andExpect(view().name("examplePage"))
			.andExpect(model().attributeExists("person"))
			.andExpect(model().attributeExists("today"));
	}
}