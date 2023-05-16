package com.backend.TaskAuthentication;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import com.backend.TaskModel.WorkDo.GroupofWork;
import com.backend.TaskModel.WorkDo.TypesofWork;
import com.backend.TaskModel.WorkDo.WorkPriority;
import com.backend.TaskModel.WorkDo.WorkStatus;
import com.backend.TaskModel.WorkDo.Overtime;

@Data
public class WorkDoRequest {
	
	private String code;
	private String name;
	
	private String workitem;
	private String module;
	private String project;
	
	private TypesofWork typeofwork;
	private WorkPriority priority;
	private GroupofWork groupofwork;
	
	private String emp3;
	private String emp4;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateStart;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateEnd;
	private String content;
	
	private WorkStatus status;
	
	private String note;
	
	private Overtime ot;

}
