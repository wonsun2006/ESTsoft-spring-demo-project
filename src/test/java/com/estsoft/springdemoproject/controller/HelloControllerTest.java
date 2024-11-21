package com.estsoft.springdemoproject.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.estsoft.springdemoproject.interf.InterDependencyService;
import com.estsoft.springdemoproject.service.HelloService;

@ExtendWith(MockitoExtension.class)
class HelloControllerTest {
	@InjectMocks
	HelloController helloController;

	@Mock
	private HelloService helloService;

	@Mock
	private InterDependencyService dependencyService;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
	}

	@Test
	void hello() throws Exception {
		when(helloService.printHello("Spring")).thenReturn("Hello, Spring!");

		mockMvc.perform(get("/hello").param("param", "Spring"))
			.andExpect(status().isOk())
			.andExpect(content().string("Hello, Spring!"));

		verify(dependencyService, times(1)).printMethod();
	}
}