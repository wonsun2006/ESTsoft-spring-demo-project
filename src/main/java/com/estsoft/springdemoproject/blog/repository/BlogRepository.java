package com.estsoft.springdemoproject.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estsoft.springdemoproject.blog.domain.Article;

@Repository
public interface BlogRepository extends JpaRepository<Article, Long> {

}
