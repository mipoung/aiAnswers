package com.aiAnswers.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.aiAnswers.service.UserService;

@Controller
@SessionAttributes("loginUser")
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@ModelAttribute("loginUser") // 이 컨트롤러에서 세션 저장후 사용
    public String populateLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // 현재 사용자의 이름 반환
    }
	
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
    
    @GetMapping("/access-denied")
    public String accessDeniedVw() {
		return "accessDenied";
    }
    
    @PostMapping("/userNameDupChk")
    public ResponseEntity<?> userNameDupChk(@RequestBody Map<String, Object> params) {
        String username = (String) params.get("username");
        
        if (username == null || username.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("아이디를 입력해주세요.");
        }
        
        if (username.length() < 4 || username.length() > 20) {
            return ResponseEntity.badRequest().body("아이디는 4자에서 20자 사이여야 합니다.");
        }
        
        int result = userService.userNameDupChk(params);

        if (result > 0) {
            return ResponseEntity.ok().body("이미 사용중인 아이디입니다. 다른 아이디를 입력해주세요.");
        } else {
            return ResponseEntity.ok().body("사용 가능한 아이디입니다.");
        }
    }
    
    @PostMapping("/userInsert")
    public ResponseEntity<?> userInsert(@RequestBody Map<String, Object> params) {
    	System.out.println(params);
    	
    	String username = (String) params.get("username");
	    String rawPassword = (String) params.get("password");
	    String email = (String) params.get("email");

	    // 유효성 검사
	    if (username == null || username.isEmpty()) {
	        return ResponseEntity.badRequest().body("아이디는 필수입니다.");
	    }
	    
	    // 아이디가 영문자와 숫자만으로 구성되어 있는지 검사
	    if (!username.matches("^[A-Za-z0-9]+$")) {
	        return ResponseEntity.badRequest().body("아이디는 영문자와 숫자만 입력 가능합니다.");
	    }
	    
	    if (rawPassword == null || rawPassword.length() < 6 || !rawPassword.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>~`_\\-+=\\[\\]\\\\/]).+$")) {
	        return ResponseEntity.badRequest().body("비밀번호는 6자리 이상이며 영문자와 숫자를 포함해야 합니다.");
	    }

	    if (email == null || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
	        return ResponseEntity.badRequest().body("유효한 이메일 형식이 아닙니다.");
	    }

	    // 여기서 DB에 사용자 추가하는 로직 추가...
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    String password = encoder.encode(rawPassword);
	    //String password = passwordEncoder.encode(rawPassword);
	    
	    params.put("password", password);
	    
	    int result = userService.userInsert(params);

	    if(result > 0) {
	    	return ResponseEntity.ok("회원가입이 완료되었습니다.");
	    } else {
	    	return ResponseEntity.ok("회원가입에 실패하였습니다.");
	    }

    }
    
    
}

