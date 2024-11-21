package com.estsoft.springdemoproject.book.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.estsoft.springdemoproject.book.domain.Book;
import com.estsoft.springdemoproject.book.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;

	@Test
	void findAll() {
		// given
		List<Book> books = List.of(new Book("123", "name", "author"));
		when(bookRepository.findAll(any(Sort.class))).thenReturn(books);

		// when
		List<Book> result = bookService.findAll();

		// then
		assertEquals(1, result.size());
		assertEquals("123", result.get(0).getId());
		assertEquals("name", result.get(0).getName());
		assertEquals("author", result.get(0).getAuthor());
	}

	@Test
	void findById() {
		// given
		String id = "123";
		Book book = new Book(id, "name", "author");
		when(bookRepository.findById(id)).thenReturn(Optional.of(book));

		// when
		Book result = bookService.findById(id);

		// then
		assertNotNull(result);
		assertEquals(id, result.getId());
		assertEquals("name", result.getName());
		assertEquals("author", result.getAuthor());
	}

	@Test
	void saveOne() {
		// given
		Book book = new Book("123", "name", "author");
		when(bookRepository.save(book)).thenReturn(book);

		// when
		Book result = bookService.saveOne(book);

		// then
		assertNotNull(result);
		assertEquals("123", result.getId());
		assertEquals("name", result.getName());
		assertEquals("author", result.getAuthor());
	}
}