package com.aiAnswers.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiAnswers.dto.UserDTO;
import com.aiAnswers.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	UserMapper userMapper;

	public int userNameDupChk(Map<String, Object> params) {
		return userMapper.userNameDupChk(params);
	}

	public int userInsert(Map<String, Object> params) {
		int result = 0;
		result = userMapper.userInsert(params);
		
		if(result > 0) {
			UserDTO user = userMapper.findByUsername((String)params.get("username"));
			params.put("userNo", user.getUserNo());
			params.put("answersCount", 100);
			userMapper.userInsertCoin(params);
			
			 // 기본 권한 할당
            String authority = "ROLE_USER"; // 기본 권한
            params.put("authority", authority);
            
            userMapper.insertAuthority(params); // 권한 추가
		}
		
		
		return result;
	}

}
