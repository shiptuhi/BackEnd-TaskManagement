package com.backend.TaskModel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Table(name = "NHAN_VIEN")
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "TEN_TAI_KHOAN", nullable = false)
	private String username;

	@Column(name = "MAT_KHAU", nullable = false)
	@JsonIgnore
	private String password;

	@Column(name = "HO_TEN", nullable = false)
	private String name;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "DIEN_THOAI", nullable = false)
	private String phoneNo;

	@Column(name = "GIOI_TINH", nullable = false)
	private Gender gender;

	public enum Gender {
		Female, Male, Other;
	}

	@Column(name = "PHONG_BAN", nullable = false)
	private String department;

	@Column(name = "TRANG_THAI", nullable = false)
	private Status status;

	public enum Status {
		Inactive, Active;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "NHAN_VIEN_CHUC_VU"
		, joinColumns = @JoinColumn(name = "NHAN_VIEN_ID", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "CHUC_VU_ID", referencedColumnName = "id"))
	private Set<Role> role = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="emp")
	@JsonIgnore
    private List<Module> mods;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "emp",orphanRemoval=true)
	@JsonBackReference
	private List<Project> project;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "emp_workitem_1",orphanRemoval=true)
	@JsonBackReference
	private Set<WorkItem> wi1;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "emp_workitem_2",orphanRemoval=true)
	@JsonBackReference
	private Set<WorkItem> wi2;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "emp_workdo_3",orphanRemoval=true)
	@JsonBackReference
	private Set<WorkDo> wd3;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "emp_workdo_4",orphanRemoval=true)
	@JsonBackReference
	private Set<WorkDo> wd4;
	
}