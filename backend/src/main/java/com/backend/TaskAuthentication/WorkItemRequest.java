package com.backend.TaskAuthentication;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import com.backend.TaskModel.WorkItem.WorkPriority;
import com.backend.TaskModel.WorkItem.WorkStatus;;

@Data
public class WorkItemRequest {
	
	private String code;
	private String name;
	private String module;
	private String project;
	private WorkPriority priority;
	private String emp1;
	private String emp2;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateStart;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateEnd;
	private String content;
	private WorkStatus status;
	

}
