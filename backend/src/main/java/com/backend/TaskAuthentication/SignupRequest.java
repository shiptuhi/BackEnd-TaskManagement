package com.backend.TaskAuthentication;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.backend.TaskModel.Employee.Gender;
import com.backend.TaskModel.Employee.Status;

import lombok.Data;

@Data
public class SignupRequest {
	@NotBlank
	private String username;
	
	private String current_password;
	
	private String new_password;

	private String verify_password;
	
	private String name;
	private String email;
	private String phoneNo;
	private Gender gender;
	private String department;
	private Status status;
	private Set<String> role;
}
