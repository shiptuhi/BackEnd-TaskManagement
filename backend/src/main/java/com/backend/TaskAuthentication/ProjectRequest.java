package com.backend.TaskAuthentication;

import java.util.Date;

import lombok.Data;

import com.backend.TaskModel.Project.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class ProjectRequest {
	
	private String code;
	private String name;
	private Status status;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dateStart;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dateEnd;
	private long empid;
	private long customerid;
	private String note;

}
