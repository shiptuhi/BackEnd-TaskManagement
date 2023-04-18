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
@Table(name = "MUC_DO_UU_TIEN")
@Data
public class WorkPriority {
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "MUC_DO_UU_TIEN", nullable = false)
	private String priority_name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "priority")
	@JsonBackReference
	private Set<WorkItem> workitem;
	
//	@OneToMany(mappedBy = "priority")
//	@JsonBackReference
//	private Set<WorkDo> workdo;

}
