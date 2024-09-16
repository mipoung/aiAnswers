package com.aiAnswers.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aiAnswers.dto.CategoriesDTO;
import com.aiAnswers.dto.QuestionDTO;
import com.aiAnswers.dto.RequestDTO.Content;
import com.aiAnswers.service.QuestionService;

@Controller
@SessionAttributes("loginUser")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@ModelAttribute("loginUser") // 이 컨트롤러에서 세션 저장후 사용
    public String populateLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // 현재 사용자의 이름 반환
    }
	
	@RequestMapping("/questionMainVw")
	public String questionMainVw(@RequestParam(value = "categoryNo", required = false) Integer categoriesNo, 
								Model model,
								@RequestParam(value = "page", defaultValue = "1") int page,
					            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		Map<String, Object> params = new HashMap<>();
		params.put("username", username);
		params.put("categoriesNo", categoriesNo);
		
		
		// 페이지네이션 
		int offset = (page - 1) * pageSize;
		params.put("limit", pageSize);
		params.put("offset", offset);
		
		System.out.println("limit(가져올글첫번호) : " + pageSize + "\n 건너뛸행 : "+offset);
		
		
		List<QuestionDTO> result = questionService.questionMainList(params); // 리스트 불러오기
		int questionMainListCnt = questionService.questionMainListCnt(params);
		
		for(int i = 0; i < result.size(); i++) { // 줄바꿈처리
			QuestionDTO questionDTO = result.get(i);
			String formattedContent = questionDTO.getContent().replace("\n", "<br>");
			questionDTO.setContent(formattedContent);
		}
		
		List<CategoriesDTO> categoriesList = questionService.categoriesList(username);
		
		// 총 페이지 수 계산
	    int totalPages = (int) Math.ceil((double) questionMainListCnt / pageSize);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("categoriesNo", categoriesNo);
		
		model.addAttribute("totalPages", totalPages);
		
		
		model.addAttribute("result", result);
		model.addAttribute("categoriesList", categoriesList);
		model.addAttribute("selectedCategoryNo", categoriesNo);
		
		return "question/questionMainVw";
	}
	
	
	
	
	@GetMapping("/questionInsertVw")
	public String questionInsertVw(@RequestParam(value = "categoryNo", required = false, defaultValue = "1") Integer categoriesNo, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<CategoriesDTO> categoriesList = questionService.categoriesList(authentication.getName());
		
		model.addAttribute("categoriesList", categoriesList);
		model.addAttribute("selectedCategoryNo", categoriesNo);
		
		return "question/questionInsertVw";
	}
	
	
	
	

	@PostMapping("/questionInsert")
	public String questionInsert(QuestionDTO questionDTO, RedirectAttributes redirectAttributes, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		questionDTO.setUsername(authentication.getName());
		
		System.out.println(questionDTO.getCategoriesNo());
		
		int result = questionService.questionInsert(questionDTO);
		System.out.println("성공 실패 : " + result);
		// 1이상이면 성공 
		if(result > 0) {
			redirectAttributes.addFlashAttribute("msg","문제 입력에 성공하였습니다.");
		} else {
			redirectAttributes.addFlashAttribute("msg","문제 입력에 실패하였습니다.");
		}
		
		redirectAttributes.addAttribute("categoryNo", questionDTO.getCategoriesNo());
		
		return "redirect:/questionInsertVw";
	}
	
	@PostMapping("/currentAnswersCnt")
	public ResponseEntity<?> currentAnswersCnt() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int currentAnswersCnt = questionService.currentAnswersCnt(authentication.getName());
		System.out.println(currentAnswersCnt);
		return ResponseEntity.ok().body(currentAnswersCnt);
		
	}
	
	
	@PostMapping("/deleteQuestion")
	public ResponseEntity<?> deleteQuestion(@RequestParam("questionsNo") Integer questionsNo) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setUsername(authentication.getName());
		questionDTO.setQuestionsNo(questionsNo);
		
		int result = questionService.deleteQuestion(questionDTO);
		System.out.println(result);
		return ResponseEntity.ok().body(result);
		
	}
	
	
	@PostMapping("updateQuestionVw")
	public String updateQuestionVw(@RequestParam("questionsNo") Integer questionsNo, Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setUsername(authentication.getName());
		questionDTO.setQuestionsNo(questionsNo);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap  = questionService.updateQestionVw(questionDTO);
		
		
		model.addAttribute("item", resultMap);
		
		
		return "question/questionUpdateVw";
	}
	
	
//	@PostMapping("/questionUpdate")
//	public ResponseEntity<?> questionUpdate(@RequestParam("questionsNo") Integer questionsNo){
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		QuestionDTO questionDTO = new QuestionDTO();
//		questionDTO.setUsername(authentication.getName());
//		questionDTO.setQuestionsNo(questionsNo);
//		
//		int result = questionService.updateQestion(questionDTO);
//		
//		
//		return ResponseEntity.ok().body(result);
//		
//	}
	
	
	@PostMapping("/questionUpdate")
	public ResponseEntity<Integer> questionUpdate(QuestionDTO questionDTO, RedirectAttributes redirectAttributes, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		questionDTO.setUsername(authentication.getName());
		
		System.out.println(questionDTO.getCategoriesNo());
		
		int result = questionService.updateQestion(questionDTO);
		System.out.println("성공 실패 : " + result);
		// 1이상이면 성공 
		if(result > 0) {
			redirectAttributes.addFlashAttribute("msg","문제 수정에 성공하였습니다.");
		} else {
			redirectAttributes.addFlashAttribute("msg","문제 수정에 실패하였습니다.");
		}
		
		redirectAttributes.addAttribute("categoryNo", questionDTO.getCategoriesNo());
		
		return ResponseEntity.ok().body(result);
	}
	
	
	@GetMapping("/categoriesInsertVw")
	public String categoriesInsertVw(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<CategoriesDTO> categoriesList = questionService.categoriesList(authentication.getName());
		
		model.addAttribute("categoriesList", categoriesList);
		System.out.println("카테고리 목록 : " + categoriesList);
		
		return "question/categoriesInsertVw";
	}
	
	
	@PostMapping("/categoriesInsert")
	public String categoriesInsert(@RequestParam Map<String, Object> params, RedirectAttributes redirectAttributes) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		params.put("username", authentication.getName());
		
		
		int result = questionService.categoriesInsert(params);
		
		System.out.println("파람 값 :" +params + "\n 성공여부:" + result);
		
		if(result > 0) {
			redirectAttributes.addFlashAttribute("msg","카테고리 추가를 성공하였습니다.");
		} else {
			redirectAttributes.addFlashAttribute("msg","카테고리 추가를 실패하였습니다.");
		}
		
		return "redirect:/categoriesInsertVw";
		
	}
	
	@PostMapping("/deleteCategories")
	public ResponseEntity<?> deleteCategories(@RequestParam Map<String, Object> params) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		params.put("username", authentication.getName());
		
		System.out.println("파람값 : " + params);
		
		int result = questionService.deleteCategories(params);
		
		return ResponseEntity.ok().body(result);
		
	}
	
	
	
	
	


}
