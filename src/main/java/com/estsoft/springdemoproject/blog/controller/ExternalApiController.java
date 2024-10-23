package com.estsoft.springdemoproject.blog.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.estsoft.springdemoproject.blog.domain.dto.ArticleRequestDTO;
import com.estsoft.springdemoproject.blog.domain.dto.CommentContent;
import com.estsoft.springdemoproject.blog.domain.dto.CommentRequestDTO;
import com.estsoft.springdemoproject.blog.domain.dto.PostContent;
import com.estsoft.springdemoproject.blog.service.BlogService;
import com.estsoft.springdemoproject.blog.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/external")
public class ExternalApiController {
	private final BlogService blogService;
	private final CommentService commentService;

	public ExternalApiController(BlogService blogService, CommentService commentService) {
		this.blogService = blogService;
		this.commentService = commentService;
	}

	@GetMapping("/articles")
	public String fetchExternalPosts(){
		RestTemplate restTemplate = new RestTemplate();

		String url = "https://jsonplaceholder.typicode.com/posts";
		// ResponseEntity<String> json = restTemplate.getForEntity(url, String.class);
		// log.info("StatusCode: {}", json.getStatusCode());
		// log.info(json.getBody());

		ResponseEntity<List<PostContent>> resultList = restTemplate.exchange(url, HttpMethod.GET, null,
			new ParameterizedTypeReference<>() {});
		// log.info("code: {}", resultList.getStatusCode());
		// log.info("{}", resultList.getBody());

		if(resultList.getStatusCode().is2xxSuccessful()){
			for(PostContent postContent : resultList.getBody()){
				blogService.saveArticle(new ArticleRequestDTO(postContent.getId(), postContent.getTitle(), postContent.getBody()));
			}
		}else{
			return "Failed";
		}

		return "OK";
	}

	@GetMapping("/comments")
	public String fetchExternalComments(){
		RestTemplate restTemplate = new RestTemplate();

		String url = "https://jsonplaceholder.typicode.com/comments";

		ResponseEntity<List<CommentContent>> resultList = restTemplate.exchange(url, HttpMethod.GET, null,
			new ParameterizedTypeReference<>() {});

		if(resultList.getStatusCode().is2xxSuccessful()){
			for(CommentContent commentContent : resultList.getBody()){
				commentService.saveComment(commentContent.getPostId(), new CommentRequestDTO(commentContent.getBody()));
			}
		}else{
			return "Failed";
		}

		return "OK";
	}
}

