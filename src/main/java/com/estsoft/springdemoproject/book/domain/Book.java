package com.estsoft.springdemoproject.book.domain;

import com.estsoft.springdemoproject.book.domain.dto.BookDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
	@Id
	private String id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String author;
}
