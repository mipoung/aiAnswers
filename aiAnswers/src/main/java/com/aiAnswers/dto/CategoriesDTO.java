package com.aiAnswers.dto;

import java.security.Timestamp;

import lombok.Data;

@Data
public class CategoriesDTO {
	private int categoriesNo;
	private int userNo;
	private String name;
	private Timestamp createdAt;

	private String questionsCnt;
}
