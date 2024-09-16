package com.aiAnswers.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.aiAnswers.dto.HistoryDTO;
import com.aiAnswers.mapper.QuestionMapper;
import com.aiAnswers.service.DevHistService;
import com.aiAnswers.service.GeminiService;
import com.aiAnswers.service.QuestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gemini")
public class GeminiController {
	
	 private final GeminiService geminiService;
	 	/*
	    @GetMapping("/chat")
	    public ResponseEntity<?> gemini() {
	        try {
	            return ResponseEntity.ok().body(geminiService.getContents("안녕! 너는 누구야?"));
	        } catch (HttpClientErrorException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }
	    */
	 	@Autowired
	 	QuestionService questionService;
	 
	    @PostMapping("/chat")
	    public ResponseEntity<?> gemini(@RequestBody Map<String, String> params) {
	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	
	    	int result = questionService.minusAnswersCnt(authentication.getName()); // username으로 count1 차감
	    	if(result == -1 || result < 0) { return ResponseEntity.badRequest().body("남은 코인이 없어요"); }
	    	
	    	System.out.println("차감결과 : " + result + "개 차감");
	    	
	    	System.out.println("==============================");
	    	System.out.println(authentication.getName()); 
	    	System.out.println("==============================");
	    	
	    	String message = params.get("message");
	    	String type = params.get("type");
	    	
	    	
	    	
	    	if(GeminiParamsNullChk(params)) {
	    		if(type.equals("answer")) {
	    			// 답변하기
	    			message += "\n\n 위 내용을 읽고 원리부터 이해할 수 있도록 차근차근 설명해 줘.";
	    			message += "\n\n 만약 어려워 보이는 용어가 있으면 용어에 대해서도 설명해 줘.";
	    			message += "\n 존댓말을 사용하고 ~입니다. ~됩니다. 같은 말투를 써.";
	    			message += "\n 답은 첫 문장으로 말해줘.";
	    			message += "\n 원리를 설명할 때는 자세하고 쉽게 설명하고 어려운 단어는 풀어서 설명해.";
	    			message += "\n 마지막에 이 문제를 풀기 위해서 알아야 할 가장 중요한 부분을 한 문장으로 알려줘.";
	    			message += "\n 이 문제는 어떤 파트에 대한 문제인지 제일 마지막에 한단어로 말해주고 파트: 이런식으로 말해.";
	    			
	    		} else if(type.equals("hint")) {
	    			// 힌트주기
	    			message += "\n 위 내용을 읽고 문제에 대한 구체적인 힌트를 비유적으로 말해주되 답을 직접적으로 말하지는 마.";
	    			message += "\n 답은 절대로 알려주면 안돼.";
	    			message += "\n 존댓말을 사용하고 ~입니다. ~됩니다. 같은 말투를 써.";
	    			message += "\n 이 문제는 어떤 파트에 대한 문제인지 제일 마지막에 한단어로 말해주고 파트: 이런식으로 말해.";
	    			
	    		} else if(type.equals("common")) {
	    			message += "\n 친구에게 얘기하듯이 얘기해 줘.";
	    		} else if(type.equals("myAnswer")) {
	    			String myAnswer = params.get("myAnswer");
	    			
	    			message += "\n 위 내용은 질문 혹은 문제야. \n";
	    			
	    			message += "\n 이 내용은 질문에 대한 답변이야. 답변:" + myAnswer;
	    			message += "\n 이 답변을 보고 정답인지 아닌지 알려주고, 몇 점 짜리 답변인지도 대답해줘. 점수는 100점이 만점이야.";
	    			message += "\n 답변의 내용에 대해서 보완해야 할 부분을 말해주고 잘못 이해한 부분을 고쳐줘.";
	    			
	    			message += "\n\n 질문 내용에 대해서는 차근차근 설명해 줘.";
	    			message += "\n\n 만약 어려워 보이는 용어가 있으면 용어에 대해서도 설명해 줘.";
	    			message += "\n 존댓말을 사용하고 ~입니다. ~됩니다. 같은 말투를 써.";
	    			message += "\n 답은 첫 문장으로 말해줘.";
	    			message += "\n 원리를 설명할 때는 자세하고 쉽게 설명하고 어려운 단어는 풀어서 설명해.";
	    			message += "\n 마지막에 이 문제를 풀기 위해서 알아야 할 가장 중요한 부분을 한 문장으로 알려줘.";
	    			message += "\n 이 문제는 어떤 파트에 대한 문제인지 제일 마지막에 한단어로 말해주고 파트: 이런식으로 말해.";
	    			
	    		}
	    		
	    	} else {
	    		return ResponseEntity.badRequest().body("메세지 내용이 없어요");
	    	}
	    	
	    	
	    	
	        try {
	        	HistoryDTO history = new HistoryDTO();
//	        	if(type.equals("common")) {
//		            	
//		            	history.setTitle(message);
//		            	questionService.insertHistroy(history);
//		        }
	            String response = geminiService.getContents(message);
	            
	            
	            if(type.equals("common")) {
	            	history.setTitle(message);
	            	history.setContent(response);
	            	history.setUsername(authentication.getName());
	            	questionService.insertHistroy(history);
	            }
	           
	            
	            //htmlResponse = response.replace("```", "<code>").replace("```", "</code>");
	            return ResponseEntity.ok().body(response);
	        } catch (HttpClientErrorException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }
	    
	    
	    
	    
	    
	    
	    // null 체크
	    public boolean GeminiParamsNullChk(@RequestBody Map<String, String> params) {
	    	if ((params.get("message") != null && !"".equals(params.get("message"))) 
	    			&& (params.get("type") != null && !"".equals(params.get("type")))) {
				return true;
			} else {
				return false;
			}
	    }
	    
	    
	
}
