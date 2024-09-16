package com.aiAnswers.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.aiAnswers.dto.DevHistDTO;
import com.aiAnswers.dto.QuestionDTO;

@Mapper
public interface DevHistMapper {

	@Select("SELECT * FROM DEV_HIST")
    List<DevHistDTO> findAll();

	@Insert("INSERT INTO DEV_HIST (TITLE, CONTENT, AUTHOR) VALUES ('개발기록', #{content}, 'admin')")
	int devHistoryInsert(Map<String, Object> params);
	
	//@Select("SELECT * FROM QUESTION")
    //Question findById(int id);
}
