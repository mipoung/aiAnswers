package com.aiAnswers.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiAnswers.dto.CategoriesDTO;
import com.aiAnswers.dto.HistoryDTO;
import com.aiAnswers.dto.QuestionDTO;
import com.aiAnswers.mapper.QuestionMapper;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionMapper questionMapper;
	
	@Override
	public List<QuestionDTO> findAll() {
		return questionMapper.findAll();
	}
	

	@Override
	public int currentAnswersCnt(String username) {
		int currentAnswerCount = questionMapper.answersCnt(username);
		return currentAnswerCount;
	}

	@Override
	public int minusAnswersCnt(String username) {
		int currentAnswerCount = questionMapper.answersCnt(username);
		int result = 0;
		System.out.println(currentAnswerCount + "개 남았음");
		
		if(currentAnswerCount == 0 || currentAnswerCount < 0) {
			result = -1;
		} else {
			int MinusAnswersCnt = questionMapper.minusAnswersCnt(username);
			result = MinusAnswersCnt;
		}
		
		
		return result;
	}


	@Override
	public List<CategoriesDTO> categoriesList(String username) {
		return questionMapper.categoriesList(username); 
	}


	@Override
	public List<QuestionDTO> questionMainList(Map<String, Object> params) {
		return questionMapper.questionMainList(params);
	}


	@Override
	public int questionInsert(QuestionDTO questionDTO) {
		return questionMapper.questionInsert(questionDTO);
	}


	@Override
	public int deleteQuestion(QuestionDTO questionDTO) {
		return questionMapper.deleteQuestion(questionDTO);
	}


	@Override
	public int updateQestion(QuestionDTO questionDTO) {
		return questionMapper.updateQuestion(questionDTO);
	}


	@Override
	public Map<String, Object> updateQestionVw(QuestionDTO questionDTO) {
		return questionMapper.updateQuestionVw(questionDTO);
	}


	@Override
	public int insertHistroy(HistoryDTO history) {
		return questionMapper.insertHistory(history);
		
	}


	@Override
	public int questionMainListCnt(Map<String, Object> params) {
		return questionMapper.questionMainListCnt(params);
	}


	@Override
	public int categoriesInsert(Map<String, Object> params) {
		return questionMapper.categoriesInsert(params);
	}


	@Override
	public int deleteCategories(Map<String, Object> params) {
		int result = 0;
		result = questionMapper.deleteQuestionBeforeDeleteCategories(params);
		result = questionMapper.deleteCategories(params);
		return result;
	}


	@Override
	public QuestionDTO randomQuestion(Map<String, Object> params) {
		return questionMapper.randomQuestion(params);
	}

	
	


}
