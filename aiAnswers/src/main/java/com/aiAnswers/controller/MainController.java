package com.aiAnswers.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.aiAnswers.dto.DevHistDTO;
import com.aiAnswers.dto.QuestionDTO;
import com.aiAnswers.mapper.DevHistMapper;
import com.aiAnswers.service.DevHistService;
import com.aiAnswers.service.GeminiService;
import com.aiAnswers.service.QuestionService;

@Controller
@SessionAttributes("loginUser")
public class MainController {
	@Autowired
	QuestionService questionService;
	
	@Autowired
	DevHistMapper devHistMapper;
	
	@Autowired
	DevHistService devHistService;
	
	
	@GetMapping("/")
	public String mainView(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getName()); 
		// 로그인 사용자 정보를 세션에 저장
		 if (authentication == null || "anonymousUser".equals(authentication.getName())) {
		        // 인증 정보가 없거나 익명 사용자일 경우
	        model.addAttribute("loginUser", "anonymousUser");
	    } else {
	        // 인증된 사용자일 경우
	        
	        Map<String, Object> params = new HashMap<>();
	        params.put("username", authentication.getName());
	        QuestionDTO result = questionService.randomQuestion(params);
	        
	        model.addAttribute("loginUser", authentication.getName()); // 대소문자 일치
	       
	        if (result != null) {
	            String formattedContent = result.getContent().replace("\n", "<br>");
	            result.setContent(formattedContent);
	            model.addAttribute("result", result);
	        } else {
	            // result가 null인 경우 처리, 예를 들어 메시지 추가
	            model.addAttribute("result", null); // 필요에 따라 null로 설정
	            model.addAttribute("errorMessage", "현재 생성된 문제가 없습니다");
	        }
	    }
		
		return "main";
	}
	
	@GetMapping("/userInsertVw")
    public String userInsertView() {
        return "user/userInsertVw"; 
    }
	
	@GetMapping("/devHistory")
	public String devHistory(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Authorities: " + authentication.getAuthorities());
		
		List<DevHistDTO> result = devHistMapper.findAll();
		System.out.println(result);
		model.addAttribute("result", result);
		return "devHistory";
	}
	
	@PostMapping("/devHistoryInsert")
	public ResponseEntity<?> devHistoryInsert(@RequestParam Map<String, Object> params){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		params.put("username", authentication.getName());
		int result = 0;
		
		System.out.println("파람값 : "+params);
		
		if("admin".equals(authentication.getName())) {
			result = devHistMapper.devHistoryInsert(params);
		} else {
			ResponseEntity.status(HttpStatus.FORBIDDEN).body("권한이 없습니다.");
		}
		
		return ResponseEntity.ok().body(result);
	}
	
	
	
	
}
