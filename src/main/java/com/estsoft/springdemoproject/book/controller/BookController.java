package com.estsoft.springdemoproject.book.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.springdemoproject.book.domain.Book;
import com.estsoft.springdemoproject.book.domain.dto.BookDTO;
import com.estsoft.springdemoproject.book.repository.BookRepository;
import com.estsoft.springdemoproject.book.service.BookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {
	private final BookService bookService;

	@PostMapping
	public String addBook(@RequestParam String id,
		@RequestParam String name,
		@RequestParam String author){
		bookService.saveOne(new Book(id,name,author));

		return "redirect:/books";  // GET /books  3xx
	}

	@GetMapping
	public String showAll(Model model){
		List<BookDTO> list = bookService.findAll()
			.stream()
			.map(BookDTO::new)
			.toList();
		model.addAttribute("bookList", list);
		return "bookManagement";
	}

	@GetMapping("/{id}")
	public String showOne(@PathVariable String id, Model model){
		Book book = bookService.findById(id);
		model.addAttribute("book", new BookDTO(book));
		return "bookDetail";
	}
}
