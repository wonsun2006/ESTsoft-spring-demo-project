package com.estsoft.springdemoproject.book.domain.dto;

import com.estsoft.springdemoproject.book.domain.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class BookDTO {
	private String id;
	private String name;
	private String author;

	public BookDTO(Book book){
		this.id = book.getId();
		this.name = book.getName();
		this.author = book.getAuthor();
	}
}
