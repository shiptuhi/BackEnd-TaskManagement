package com.backend.TaskAuthentication;

import lombok.Data;
import com.backend.TaskModel.Employee;

@Data
public class JwtAuthenticationResponse {
	private String accessToken;
//	private String tokenType = "Bearer ";
	private User user;

	class User {
		public Long id;
		public String username;
		public String name;
		public String mail;
		public String phone;

		User(Long id, String username, String name, String mail, String phone) {
			this.id = id;
			this.username = username;
			this.name = name;
			this.mail = mail;
			this.phone = phone;
		}

	}

	public JwtAuthenticationResponse(String accessToken, Employee user) {
		this.accessToken = accessToken;
		User u = new User(user.getId(), user.getUsername(), user.getName(), user.getEmail(), user.getPhoneNo());
		this.user = u;
	}

}
