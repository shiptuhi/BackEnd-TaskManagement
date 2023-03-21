package com.backend.TaskAuthentication;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SigninRequest {
	@NotBlank
	private String username;

	@NotBlank
	private String password;

}
