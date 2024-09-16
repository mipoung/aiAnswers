package com.aiAnswers.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.aiAnswers.dto.UserDTO;
import com.aiAnswers.mapper.UserMapper;
import com.aiAnswers.service.CustomUserDetails;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	/*
	 스프링 시큐리티의 주요 설정을 담당하는 클래스입니다. 주요 메서드와 설정 항목은 다음과 같습니다.

	SecurityFilterChain: 필터 체인을 설정합니다.
	PasswordEncoder: 비밀번호 인코딩 방식을 설정합니다.
	UserDetailsService: 사용자 정보를 제공하는 서비스입니다. 
	 */
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http	.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 세션 생성 정책
                .maximumSessions(1) // 최대 세션 수
                .expiredUrl("/login?expired") // 세션 만료 시 리다이렉트할 URL 
                )
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/login", "/error").permitAll()
                             //   .requestMatchers("/").permitAll()
                                .requestMatchers("/js/common/**", "/css/common/**", "/images/**", "/userInsertVw", "/userNameDupChk", "/userInsert", "/").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/devHistory").hasRole("ADMIN") // USER 권한 필요
                                //.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                                .anyRequest().authenticated()
                                
                                /* /admin/**: 이 경로는 ADMIN 역할을 가진 사용자만 접근할 수 있습니다.
                                /user/**: 이 경로는 USER 또는 ADMIN 역할을 가진 사용자가 접근할 수 있습니다. */
                )
                .formLogin(form -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/", true) // 로그인 후 리다이렉트 되는 페이지
                                .permitAll()
                )
                .logout(logout -> logout
                				.logoutUrl("/logout")    
                                .logoutSuccessUrl("/login")
                                .invalidateHttpSession(true)           // 세션 무효화
                                .deleteCookies("JSESSIONID")           // 로그아웃 시 쿠키 삭제
                                .permitAll()
                ).exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/access-denied")  // 권한이 없을 경우 리다이렉트할 페이지 설정
                ).csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
//    @Bean
//    public UserDetailsService userDetailsService(UserMapper userMapper) {
//        return username -> {
//            UserDTO user = userMapper.findByUsername(username);
//            if (user == null) {
//                throw new UsernameNotFoundException("User not found");
//            }
//            return new CustomUserDetails(user);
//        };
//    }
    
    @Bean
    public UserDetailsService userDetailsService(UserMapper userMapper) {
        return username -> {
            // 사용자 정보를 DB에서 가져옴
            UserDTO user = userMapper.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
            
            // 사용자 권한을 DB에서 가져옴
            List<GrantedAuthority> authorities = userMapper.findAuthoritiesByUserNo(user.getUserNo())
                                                           .stream()
                                                           .map(SimpleGrantedAuthority::new)
                                                           .collect(Collectors.toList());

            // CustomUserDetails 생성 시 권한 정보도 함께 전달
            return new CustomUserDetails(user, authorities);
        };
    }
}

