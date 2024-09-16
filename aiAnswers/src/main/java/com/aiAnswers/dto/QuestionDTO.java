package com.aiAnswers.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class QuestionDTO {
//    private int no;
//    private String title;
//    private String content;
//    private String author;
//    private Timestamp createdAt;
//    private Timestamp updatedAt;
    
    private int questionsNo;     // PRIMARY KEY
    private int categoriesNo;    // FOREIGN KEY to CATEGORIES
    private String title;
    private String content;
    private int userNo;       // FOREIGN KEY to USER
    private String username;
    private String createdAt;
    private String updatedAt;
    private String rowNum;
    private int checkCnt;
}
