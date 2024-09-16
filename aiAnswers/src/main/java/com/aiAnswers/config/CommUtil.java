package com.aiAnswers.config;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component
public class CommUtil {

    private static final Parser parser = Parser.builder().build();
    private static final HtmlRenderer renderer = HtmlRenderer.builder().build();

    /**
     * 마크다운 문자열을 HTML로 변환합니다.
     * 
     * @param markdown 마크다운 문자열
     * @return 변환된 HTML 문자열
     */
    public static String convertMarkdownToHtml(String markdown) {
        Node document = parser.parse(markdown);
        return renderer.render(document);
    }
    
    
}
