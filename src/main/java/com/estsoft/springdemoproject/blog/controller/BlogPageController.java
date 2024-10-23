package com.estsoft.springdemoproject.blog.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.estsoft.springdemoproject.blog.domain.Article;
import com.estsoft.springdemoproject.blog.domain.dto.ArticleViewResponse;
import com.estsoft.springdemoproject.blog.service.BlogService;

@Controller
public class BlogPageController {
	private final BlogService blogService;

	public BlogPageController(BlogService blogService) {
		this.blogService = blogService;
	}

	@GetMapping("/articles")
	public String showArticle(Model model){
		List<Article> articleList = blogService.findAll();

		List<ArticleViewResponse> list = articleList.stream().map(ArticleViewResponse::new).toList();

		model.addAttribute("articles", list);

		return "articleList";
	}

	@GetMapping("/articles/{id}")
	public String showDetails(@PathVariable Long id, Model model){
		// Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// Principal principal = (Principal)authentication.getPrincipal();

		Article article = blogService.findById(id);

		ArticleViewResponse response = new ArticleViewResponse(article);

		model.addAttribute("article", response);

		return "article";
	}

	@GetMapping("/new-article")
	public String addArticle(@RequestParam(required = false) Long id, Model model){
		if (id==null) {
			model.addAttribute("article", new ArticleViewResponse());
		} else {
			Article article = blogService.findById(id);
			model.addAttribute("article", new ArticleViewResponse(article));
		}

		return "newArticle";
	}
}
