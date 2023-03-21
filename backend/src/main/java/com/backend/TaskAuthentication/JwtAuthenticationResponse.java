package com.backend.TaskAuthentication;

import lombok.Data;
import com.backend.TaskModel.Employee;

@Data
public class JwtAuthenticationResponse {
	private String accessToken;
//	private String tokenType = "Bearer ";
	private Employee emp;

	public JwtAuthenticationResponse(String accessToken, Employee emp) {
		this.accessToken = accessToken;
		this.emp = emp;
	}

}
