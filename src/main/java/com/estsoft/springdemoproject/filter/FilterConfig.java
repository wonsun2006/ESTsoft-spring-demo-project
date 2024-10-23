package com.estsoft.springdemoproject.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;

@Configuration
public class FilterConfig {
	@Bean
	public FilterRegistrationBean<Filter> firstFilter(){
		FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();

		filter.setFilter(new FirstFilter());
		filter.setOrder(1);
		filter.addUrlPatterns("/books");

		return filter;
	}

	@Bean
	public FilterRegistrationBean<Filter> secondFilter(){
		FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();

		filter.setFilter(new SecondFilter());
		filter.setOrder(2);

		return filter;
	}
}
