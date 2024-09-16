package com.aiAnswers.dto;

import lombok.Data;

@Data
public class UserDTO {
	private String userNo;
    private String username;
    private String password;
    private boolean enabled;
    private String email;
    private String createdDate;
    private String updatedDate;

    // getters and setters
}
