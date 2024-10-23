package com.estsoft.springdemoproject.repository;

import java.sql.Date;
import java.time.Instant;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "age", nullable = false)
	private Integer age;

	@Column(name = "`desc`")
	private String desc;

	@Column(name = "created_at")
	private Date createdAt;

	public Student(){
	}

	public Student(Long id, String name, Integer age, String desc, Date createdAt) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.desc = desc;
		this.createdAt = createdAt;
	}
}