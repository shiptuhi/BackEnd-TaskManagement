package com.backend.TaskModel;

import java.util.Set;

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
@Table(name = "NHOM_CONG_VIEC")
@Data
public class GroupofWork {
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "NHOM_CONG_VIEC", nullable = false)
	private String group_name;
	
	@OneToMany(mappedBy = "group")
	@JsonBackReference
//	@JsonIgnore
	private Set<WorkDo> workdo;

}
