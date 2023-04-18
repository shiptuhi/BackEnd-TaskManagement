package com.backend.TaskModel;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "TRANG_THAI_CONG_VIEC")
@Data
public class WorkStatus {
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "TRANG_THAI", nullable = false)
	private String status_name;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "status_workitem")
	@JsonBackReference
	private Set<WorkItem> workitem;
	
//	@OneToMany(mappedBy = "status_workdo")
//	@JsonBackReference
//	private Set<WorkDo> workdo;

}
