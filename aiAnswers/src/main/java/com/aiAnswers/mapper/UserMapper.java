package com.aiAnswers.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.aiAnswers.dto.UserDTO;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USER WHERE USERNAME = #{username}")
    UserDTO findByUsername(String username);
    
    @Select("SELECT COUNT(*) FROM USER WHERE USERNAME = #{username}")
    int userNameDupChk(Map<String, Object> params);
    
    @Insert("INSERT INTO USER(USERNAME, PASSWORD, EMAIL) VALUES(LOWER(#{username}), #{password}, #{email})")
    int userInsert(Map<String, Object> params);

    @Insert("INSERT INTO USER_QUESTION_LIMIT(USER_NO, ANSWERS_COUNT) VALUES(#{userNo}, #{answersCount})")
	int userInsertCoin(Map<String, Object> params);
    
//    @Insert("INSERT INTO AUTHORITIES (USER_NO, AUTHORITY) VALUES (#{userNo}, #{authority})")
//    int insertAuthority(@Param("userNo") String userNo, @Param("authority") String authority);
    
    @Insert("INSERT INTO AUTHORITIES (USER_NO, AUTHORITY) VALUES (#{userNo}, #{authority})")
    int insertAuthority(Map<String, Object> params);
    
    @Select("SELECT AUTHORITY FROM AUTHORITIES WHERE USER_NO = #{userNo}")
    List<String> findAuthoritiesByUserNo(String userNo);

}

