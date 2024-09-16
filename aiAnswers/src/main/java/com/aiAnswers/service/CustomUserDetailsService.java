package com.aiAnswers.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import com.aiAnswers.dto.UserDTO;
import com.aiAnswers.mapper.UserMapper;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UserMapper userMapper;
    
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDTO user = userMapper.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return new CustomUserDetails(user);
//    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // DB에서 사용자 정보 가져오기
        UserDTO user = userMapper.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        

        // 사용자 권한 조회
        List<GrantedAuthority> authorities = userMapper.findAuthoritiesByUserNo(user.getUserNo())
                                                        .stream()
                                                        .map(SimpleGrantedAuthority::new)
                                                        .collect(Collectors.toList());

     // CustomUserDetails 객체로 반환
        return new CustomUserDetails(user, authorities);
    }

}
