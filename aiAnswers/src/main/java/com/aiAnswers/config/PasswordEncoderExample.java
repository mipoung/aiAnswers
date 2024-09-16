package com.aiAnswers.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderExample {
    public static void main(String[] args) {
        // BCryptPasswordEncoder 객체 생성
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // 암호화할 비밀번호
        String rawPassword = "123123";
        
        // 비밀번호 암호화
        String encodedPassword = encoder.encode(rawPassword);
        
        // 암호화된 비밀번호 출력
        System.out.println("Encoded Password: " + encodedPassword);
        System.out.println("2");
    }
}
