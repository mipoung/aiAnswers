package com.aiAnswers.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.aiAnswers.dto.CategoriesDTO;
import com.aiAnswers.dto.HistoryDTO;
import com.aiAnswers.dto.QuestionDTO;

@Mapper
public interface QuestionMapper {

	@Select("SELECT * FROM QUESTIONS")
    List<QuestionDTO> findAll();
	
	int answersCnt(@Param("username") String username);
	
	int minusAnswersCnt(@Param("username") String username);
	
	List<CategoriesDTO> categoriesList(@Param("username") String username);
	
	List<QuestionDTO> questionMainList(Map<String, Object> params);
	
	int questionInsert(QuestionDTO questionDTO);
	
	int deleteQuestion(QuestionDTO questionDTO);
	
	int updateQuestion(QuestionDTO questionDTO);

	Map<String, Object> updateQuestionVw(QuestionDTO questionDTO);

	int insertHistory(HistoryDTO history);

	int questionMainListCnt(Map<String, Object> params);

	int categoriesInsert(Map<String, Object> params);

	int deleteCategories(Map<String, Object> params);

	int deleteQuestionBeforeDeleteCategories(Map<String, Object> params);

	QuestionDTO randomQuestion(Map<String, Object> params);

	
	/*
	 * 
	 * INSERT INTO USER_QUESTION_LIMIT (USER_NO, ANSWERS_COUNT)
VALUES ((SELECT USER_NO FROM USER WHERE USERNAME = 'admin'), 10);

update USER_QUESTION_LIMIT
set ANSWERS_COUNT = ANSWERS_COUNT-1
where USER_NO = (SELECT USER_NO FROM USER WHERE USERNAME = 'admin');
	 */
	
	//@Select("SELECT * FROM QUESTION")
    //Question findById(int id);
}
