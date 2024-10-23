package com.estsoft.springdemoproject.book.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.estsoft.springdemoproject.book.domain.Book;
import com.estsoft.springdemoproject.book.domain.dto.BookDTO;
import com.estsoft.springdemoproject.book.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {
	private final BookRepository bookRepository;

	public List<Book> findAll(){
		// select * from book order by id;
		return bookRepository.findAll(Sort.by("id").ascending());
	}

	public Book findById(String id){
		return bookRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Wrong id."));
	}

	public Book saveOne(Book book){
		return bookRepository.save(book);
	}
}
