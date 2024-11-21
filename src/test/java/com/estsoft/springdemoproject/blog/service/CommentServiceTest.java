package com.estsoft.springdemoproject.blog.service;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.estsoft.springdemoproject.blog.domain.Article;
import com.estsoft.springdemoproject.blog.domain.Comment;
import com.estsoft.springdemoproject.blog.domain.dto.ArticleNestingCommentsDTO;
import com.estsoft.springdemoproject.blog.domain.dto.CommentRequestDTO;
import com.estsoft.springdemoproject.blog.repository.BlogRepository;
import com.estsoft.springdemoproject.blog.repository.CommentRepository;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
	@InjectMocks
	CommentService commentService;

	@Mock
	BlogRepository blogRepository;

	@Mock
	CommentRepository commentRepository;

	private static Article getArticle() {
		return new Article("title", "content");
	}

	@Test
	void saveComment() {
		// given
		String body = "body";
		CommentRequestDTO request = new CommentRequestDTO(body);
		Article article = getArticle();
		Comment comment = new Comment(body, article);

		// stub
		Mockito.doReturn(Optional.of(article)).when(blogRepository).findById(any());
		Mockito.doReturn(comment).when(commentRepository).save(any());

		// when
		Comment returnedComment = commentService.saveComment(1L, request);

		// then
		assertThat(returnedComment.getBody(), is(comment.getBody()));

		verify(commentRepository, times(1)).save(any());
	}

	@Test
	void findComment() {
		// given
		String body = "body";
		Article article = getArticle();
		Comment comment = new Comment(body, article);

		// stub
		Mockito.doReturn(Optional.of(comment)).when(commentRepository).findById(any());

		// when
		Comment returnedComment = commentService.findComment(1L);

		// then
		assertThat(returnedComment.getBody(), is(comment.getBody()));

		verify(commentRepository, times(1)).findById(any());
	}

	@Test
	void updateComment() {
		// given
		String body = "body";
		CommentRequestDTO request = new CommentRequestDTO(body);
		Article article = getArticle();
		Comment oldComment = new Comment("old", article);
		Comment newComment = new Comment(body, article);

		// stub
		Mockito.doReturn(Optional.of(oldComment)).when(commentRepository).findById(any());
		Mockito.doReturn(newComment).when(commentRepository).save(any());

		// when
		Comment returnedComment = commentService.updateComment(1L, request);

		// then
		assertThat(returnedComment.getBody(), is(newComment.getBody()));

		verify(commentRepository, times(1)).findById(any());
		verify(commentRepository, times(1)).save(any());
	}

	@Test
	void deleteComment() {
		// given

		// when
		commentService.deleteComment(1L);

		// then
		verify(commentRepository, times(1)).deleteById(any());
	}

	@Test
	void getArticleNestingComments() {
		// given
		Article article = getArticle();
		List<Comment> commentList = List.of(new Comment("body1", article), new Comment("body2", article));

		Mockito.doReturn(Optional.of(article)).when(blogRepository).findById(any());
		Mockito.doReturn(commentList).when(commentRepository).findAllByArticle(any());

		// when
		ArticleNestingCommentsDTO result = commentService.getArticleNestingComments(1L);

		// then
		assertThat(result.getTitle(), is(article.getTitle()));
		for (int i = 0; i < result.getComments().size(); i++) {
			assertThat(result.getComments().get(i).getBody(), is(commentList.get(i).getBody()));
		}

		verify(commentRepository, times(1)).findAllByArticle(any());
	}
}