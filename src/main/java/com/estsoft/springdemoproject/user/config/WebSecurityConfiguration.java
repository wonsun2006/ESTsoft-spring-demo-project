package com.estsoft.springdemoproject.user.config;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration {
	// ignore 처리 (특정 요청은 spring security 설정을 적용하지 않도록 설정)
	@Bean
	public WebSecurityCustomizer ignore(){
		return webSecurity -> webSecurity.ignoring()
			// .requestMatchers(toH2Console())	// /h2-console
			.requestMatchers("/static/**","/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html");
	}

	// 패스워드 암호화 방식 정의
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	// 특정 HTTP 요청에 대한 웹 보안 구성
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		// return httpSecurity.authorizeHttpRequests()    // 3) 인증, 인가 설정
		// 	.requestMatchers("/login", "/signup", "/user").permitAll()
		// 	.requestMatchers("/api/external").hasRole("admin") // 인가
		// 	.anyRequest().authenticated()
		// 	.and()
		// 	.formLogin()        //4) 폼 기반 로그인 설정
		// 	.loginPage("/login")
		// 	.defaultSuccessUrl("/articles")
		// 	.and()
		// 	.logout()       // 5) 로그아웃 설정
		// 	.logoutSuccessUrl("/login")
		// 	.invalidateHttpSession(true)
		// 	.and()
		// 	.csrf().disable()       // 6) csrf 비활성화
		// 	.build();
		return httpSecurity.authorizeHttpRequests(
			custom -> custom.requestMatchers("/login", "/signup", "/user").permitAll()
				// .requestMatchers("/articles/**").hasRole("ADMIN")  // 내부적으로 ROLE_ADMIN으로 변환
				// .requestMatchers("/articles/**").hasAuthority("ROLE_ADMIN")  // ROLE_ADMIN 이름 그대로 확인
				// .requestMatchers("/articles/**").hasAnyRole("ADMIN", "USER") // 여러 권한 확인
				// .requestMatchers("/articles/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER") // 여러 권한 확인
				// .anyRequest().authenticated()
				.anyRequest().permitAll()  // 편의상 설정
			)    // 인증, 인가 설정
			.formLogin(custom -> custom.loginPage("/login")
				.defaultSuccessUrl("/articles"))        // 폼 기반 로그인 설정
			.logout(custom -> custom.logoutSuccessUrl("/login")
				.invalidateHttpSession(true))       // 로그아웃 설정
			.csrf(custom -> custom.disable())			// csrf 비활성화
			.build();
	}
}
