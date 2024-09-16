package com.aiAnswers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aiAnswers.config.CommUtil;
import com.aiAnswers.dto.RequestDTO;
import com.aiAnswers.dto.ResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeminiService {
		
	@Qualifier("geminiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${gemini.api.url}")
    private String apiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;
	
	
    public String getContents(String prompt) {

        // Gemini에 요청 전송
        String requestUrl = apiUrl + "?key=" + geminiApiKey;

        RequestDTO request = new RequestDTO(prompt);
        ResponseDTO response = restTemplate.postForObject(requestUrl, request, ResponseDTO.class);

        String message = response.getCandidates().get(0).getContent().getParts().get(0).getText().toString();
        
        // 줄바꿈을 <br>로 변환
       // String htmlResponse = message.replace("\n", "<br>");
        // 마크다운을 html로 변환
        String htmlResponse = CommUtil.convertMarkdownToHtml(message);

        return htmlResponse;
    }
    
	
}
