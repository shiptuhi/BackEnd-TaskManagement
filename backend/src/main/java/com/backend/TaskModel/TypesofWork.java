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
@Table(name = "LOAI_CONG_VIEC")
@Data
public class TypesofWork {
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "LOAI_CONG_VIEC", nullable = false)
	private String work_name;
	
	@OneToMany(mappedBy = "type")
	@JsonBackReference
	private Set<WorkDo> workdo;

}
