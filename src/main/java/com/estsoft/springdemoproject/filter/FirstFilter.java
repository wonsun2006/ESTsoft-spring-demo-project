package com.estsoft.springdemoproject.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class FirstFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("FirstFilter.init()");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
		IOException,
		ServletException {
		System.out.println("FirstFilter.doFilter() request");

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		System.out.println("requestURI: " + request.getRequestURI());

		filterChain.doFilter(servletRequest, servletResponse);

		System.out.println("FirstFilter.doFilter() response");
	}

	@Override
	public void destroy() {
		System.out.println("FirstFilter.destroy()");
	}
}
