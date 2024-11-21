package com.estsoft.springdemoproject.book.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.estsoft.springdemoproject.book.domain.Book;
import com.estsoft.springdemoproject.book.domain.dto.BookDTO;
import com.estsoft.springdemoproject.book.service.BookService;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {
	@InjectMocks
	BookController bookController;

	@Mock
	BookService bookService;

	MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}

	@Test
	void addBook() throws Exception {
		// given : API 호출에 필요한 데이터 만들기
		String id = "123";
		String name = "name";
		String author = "author";
		Book book = new Book(id, name, author);

		// stub (service.saveArticle 호출 시)
		Mockito.when(bookService.saveOne(any())).thenReturn(book);

		// when : API 호출
		ResultActions resultActions = mockMvc.perform(
			post("/books")
				.param("id", id)
				.param("name", name)
				.param("author", author)
		);

		// then : 호출 결과 검증
		resultActions.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/books"));

		verify(bookService, times(1)).saveOne(any(Book.class));
	}

	@Test
	void showAll() throws Exception {
		// given
		List<Book> books = List.of(new Book("123", "name", "author"));
		List<BookDTO> bookDTOs = books.stream().map(BookDTO::new).toList();

		when(bookService.findAll()).thenReturn(books);

		// when & then
		mockMvc.perform(get("/books"))
			.andExpect(status().isOk())
			.andExpect(view().name("bookManagement"))
			.andExpect(model().attribute("bookList", hasSize(1)))
			.andExpect(model().attribute("bookList", hasItem(
				allOf(
					hasProperty("name", is(books.get(0).getName())),
					hasProperty("author", is(books.get(0).getAuthor()))
				)
			)));
	}

	@Test
	void showOne() throws Exception {
		// given
		String id = "123";
		String name = "name";
		String author = "author";
		Book book = new Book(id, name, author);
		BookDTO bookDTO = new BookDTO(book);

		when(bookService.findById(id)).thenReturn(book);

		// when & then
		mockMvc.perform(get("/books/{id}", id))
			.andExpect(status().isOk())
			.andExpect(view().name("bookDetail"))
			.andExpect(model().attribute("book",
				allOf(
					hasProperty("name", is(name)),
					hasProperty("author", is(author))
				)
			));
	}
}