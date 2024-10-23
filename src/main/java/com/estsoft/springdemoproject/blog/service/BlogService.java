package com.estsoft.springdemoproject.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.springdemoproject.blog.domain.dto.ArticleRequestDTO;
import com.estsoft.springdemoproject.blog.domain.Article;
import com.estsoft.springdemoproject.blog.repository.BlogRepository;

@Service
public class BlogService {
	private final BlogRepository repository;

	public BlogService(BlogRepository repository) {
		this.repository = repository;
	}

	// blog 게시글 저장
	public Article saveArticle(ArticleRequestDTO request){
		return repository.save(request.toEntity());
	}

	// blog 게시글 조회
	public List<Article> findAll(){
		return repository.findAll();
	}

	// blog 게시글 단건 조회
	public Article findById(Long id){
		return repository.findById(id).orElseThrow(()->new IllegalArgumentException("not found id: "+id));
	}

	// blog 게시글 삭제 (id)
	public void deleteById(Long id){
		repository.deleteById(id);
	}

	@Transactional
	public Article update(Long id, String title, String content){
		Article article = findById(id);
		article.update(title, content);

		return repository.save(article);
	}
}
