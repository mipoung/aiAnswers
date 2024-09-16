package com.aiAnswers.service;

import java.util.List;
import java.util.Map;

import com.aiAnswers.dto.CategoriesDTO;
import com.aiAnswers.dto.HistoryDTO;
import com.aiAnswers.dto.QuestionDTO;

public interface QuestionService {
	List<QuestionDTO> findAll();
	
	List<QuestionDTO> questionMainList(Map<String, Object> params);
	int currentAnswersCnt(String username);
	int minusAnswersCnt(String username);
	List<CategoriesDTO> categoriesList(String username);
	int questionInsert(QuestionDTO questionDTO);

	int deleteQuestion(QuestionDTO questionDTO);

	int updateQestion(QuestionDTO questionDTO);

	Map<String, Object> updateQestionVw(QuestionDTO questionDTO);

	int insertHistroy(HistoryDTO history);


	int questionMainListCnt(Map<String, Object> params);

	int categoriesInsert(Map<String, Object> params);

	int deleteCategories(Map<String, Object> params);

	QuestionDTO randomQuestion(Map<String, Object> params);

}
