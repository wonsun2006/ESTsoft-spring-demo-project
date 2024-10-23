package com.estsoft.springdemoproject.blog.controller;

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

import com.estsoft.springdemoproject.blog.domain.Comment;
import com.estsoft.springdemoproject.blog.domain.dto.ArticleNestingCommentsDTO;
import com.estsoft.springdemoproject.blog.domain.dto.CommentRequestDTO;
import com.estsoft.springdemoproject.blog.domain.dto.CommentResponseDTO;
import com.estsoft.springdemoproject.blog.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("/articles/{articleId}/comments")
	public ResponseEntity<CommentResponseDTO> saveCommentByArticleId(@PathVariable Long articleId, @RequestBody CommentRequestDTO request){
		Comment comment = commentService.saveComment(articleId, request);
		return ResponseEntity.status(HttpStatus.CREATED).body(new CommentResponseDTO(comment));
	}

	@GetMapping("/comments/{commentId}")
	public ResponseEntity<CommentResponseDTO> getComment(@PathVariable Long commentId){
		Comment comment = commentService.findComment(commentId);
		CommentResponseDTO response = new CommentResponseDTO(comment);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/comments/{commentId}")
	public ResponseEntity<CommentResponseDTO> editComment(@PathVariable Long commentId, @RequestBody CommentRequestDTO request){
		Comment comment = commentService.updateComment(commentId, request);
		return ResponseEntity.status(HttpStatus.CREATED).body(new CommentResponseDTO(comment));
	}

	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<CommentResponseDTO> removeComment(@PathVariable Long commentId){
		commentService.deleteComment(commentId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/articles/{articleId}/comments")
	public ResponseEntity<ArticleNestingCommentsDTO> getArticleWithCommentList(@PathVariable Long articleId){
		return ResponseEntity.ok(commentService.getArticleNestingComments(articleId));
	}
}
