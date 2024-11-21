package com.estsoft.springdemoproject.book.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
