package com.backend.TaskModel;

import java.util.List;
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
@Table(name = "KHACH_HANG")
@Data
public class Customer {	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "HO_TEN", nullable = false)
	private String name;
	
	@Column(name = "TEN_HE_THONG", nullable = false)
	private String systemName;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "DIEN_THOAI", nullable = false)
	private String phoneNo;
	
	@Column(name = "DOI_TAC", nullable = false)
	private String partner;
	
	@Column(name = "GIOI_TINH", nullable = false)
	private Gender gender;
	
	public enum Gender {
		Female, Male, Other;
	}

	@Column(name = "GHI_CHU", nullable = true)
	private String note;

	@Column(name = "TRANG_THAI", nullable = false)
	private Status status;
	
	public enum Status {
		Inactive, Active;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	@JsonBackReference
	private Set<Project> project;
	
//	@OneToMany(mappedBy = "customer_workdo")
//	@JsonBackReference
//	private Set<WorkDo> workdo;
}