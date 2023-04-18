package com.backend.TaskAuthentication;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class WorkItemRequest {
	
	private String code;
	private String name;
	private long moduleid;
	private long projectid;
	private long priorityid;
	private long emp1id;
	private long emp2id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dateStart;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dateEnd;
	private String content;
	private long status;
	

}
