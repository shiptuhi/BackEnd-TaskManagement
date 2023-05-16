package com.backend.TaskAuthentication;

import com.backend.TaskModel.Customer.Status;
import com.backend.TaskModel.Customer.Gender;
import lombok.Data;

@Data
public class CustomerRequest {
	
	private String name;
	private String systemName;
	private String email;
	private String phoneNo;
	private Gender gender;
	private String partner;
	private String note;
	private Status status;

}
