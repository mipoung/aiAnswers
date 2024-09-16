package com.aiAnswers.dto;

import java.security.Timestamp;

import lombok.Data;

@Data
public class HistoryDTO {
	private String title;
	private String content;
	private String createdAt;
	private String username;

}
