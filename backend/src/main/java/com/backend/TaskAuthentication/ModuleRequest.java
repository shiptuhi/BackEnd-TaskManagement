package com.backend.TaskAuthentication;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ModuleRequest {

	private String code;
	private String name;
	private long projectid;
	private long empid;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dateStart;
	private String note;

}

