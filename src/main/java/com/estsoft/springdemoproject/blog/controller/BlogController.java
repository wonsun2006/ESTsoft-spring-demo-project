package com.estsoft.springdemoproject.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.springdemoproject.blog.domain.dto.ArticleRequestDTO;
import com.estsoft.springdemoproject.blog.domain.Article;
import com.estsoft.springdemoproject.blog.domain.dto.ArticleDTO;
import com.estsoft.springdemoproject.blog.domain.dto.UpdateArticleRequestDTO;
import com.estsoft.springdemoproject.blog.service.BlogService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "블로그 CRUD")
@RestController
@RequestMapping("/api")
public class BlogController {
	private final BlogService service;

	public BlogController(BlogService service) {
		this.service = service;
	}

	// RequestMapping /articles
	@PostMapping("/articles")
	public ResponseEntity<ArticleDTO> writeArticle(@RequestBody ArticleRequestDTO request){
		Article article = service.saveArticle(request);
		ArticleDTO response = new ArticleDTO(article.getId(), article.getTitle(), article.getContent(), article.getCreatedAt(), article.getUpdatedAt());

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// Request Mapping 조회
	@ApiResponses(
		value = {
			@ApiResponse(responseCode = "200", description = "요청에 성공했습니다.", content = @Content(mediaType="application/json"))
		})
	@Operation(summary = "블로그 전체 목록 보기", description = "블로그 메인 화면에서 보여주는 전체 목록")
	@GetMapping("/articles")
	public ResponseEntity<List<ArticleDTO>> findAll(){
		List<Article> articleList = service.findAll();
		List<ArticleDTO> response = articleList.stream()
				.map(Article::convert)
				.toList();

		return ResponseEntity.ok(response);
	}

	@Parameter(name="id", description = "블로그 글 ID", example = "45")
	@GetMapping("/articles/{id}")
	public ResponseEntity<ArticleDTO> findById(@PathVariable Long id){
		Article article = service.findById(id);

		return ResponseEntity.ok(article.convert());
	}

	@DeleteMapping("/articles/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/articles/{id}")
	public ResponseEntity<ArticleDTO> updateById(@PathVariable Long id, @RequestBody UpdateArticleRequestDTO request){
		Article article = service.update(id, request.getTitle(), request.getContent());
		return ResponseEntity.ok(article.convert());
	}
}
