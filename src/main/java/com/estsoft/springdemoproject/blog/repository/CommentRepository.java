package com.estsoft.springdemoproject.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estsoft.springdemoproject.blog.domain.Article;
import com.estsoft.springdemoproject.blog.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
	List<Comment> findAllByArticle(Article article);
}
