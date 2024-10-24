package com.estsoft.springdemoproject.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estsoft.springdemoproject.blog.domain.Article;
import com.estsoft.springdemoproject.blog.domain.Comment;
import com.estsoft.springdemoproject.blog.domain.dto.ArticleNestingCommentsDTO;
import com.estsoft.springdemoproject.blog.domain.dto.CommentRequestDTO;
import com.estsoft.springdemoproject.blog.repository.BlogRepository;
import com.estsoft.springdemoproject.blog.repository.CommentRepository;

@Service
public class CommentService {
	private final BlogRepository blogRepository;
	private final CommentRepository commentRepository;

	public CommentService(BlogRepository blogRepository, CommentRepository commentRepository) {
		this.blogRepository = blogRepository;
		this.commentRepository = commentRepository;
	}

	public Comment saveComment(Long articleId, CommentRequestDTO request){
		Article article = blogRepository.findById(articleId).orElseThrow();
		return commentRepository.save(new Comment(request.getBody(), article));
	}

	public Comment findComment(Long commentId) {
		return commentRepository.findById(commentId).orElseThrow();
	}

	public Comment updateComment(Long commentId, CommentRequestDTO request) {
		Comment comment = findComment(commentId);
		comment.setBody(request.getBody());
		return commentRepository.save(comment);
	}

	public void deleteComment(Long commentId){
		commentRepository.deleteById(commentId);
	}

	public ArticleNestingCommentsDTO getArticleNestingComments(Long articleId){
		Article article = blogRepository.findById(articleId).orElseThrow();
		List<Comment> comments = commentRepository.findAllByArticle(article);
		return new ArticleNestingCommentsDTO(article, comments);
	}
}
