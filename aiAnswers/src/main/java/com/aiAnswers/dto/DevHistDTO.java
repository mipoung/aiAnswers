package com.aiAnswers.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class DevHistDTO {
    private int no;
    private String title;
    private String content;
    private String author;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
