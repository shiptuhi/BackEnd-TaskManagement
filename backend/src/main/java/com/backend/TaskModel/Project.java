package com.backend.TaskModel;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "DU_AN")
@Data
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "MA_DU_AN", nullable = false)
	private String code;

	@Column(name = "TEN_DU_AN", nullable = true)
	private String name;

	@Column(name = "TRANG_THAI", nullable = false)
	private Status status;

	public enum Status {
		Inactive, Active;
	}

	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name = "NGAY_BAT_DAU", nullable = true)
	private Date dateStart;

	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name = "NGAY_KET_THUC", nullable = true)
	private Date dateEnd;
	

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "NHAN_VIEN_ID", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private Employee emp;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "KHAC_HANG_ID", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private Customer customer;

	@Column(name = "GHI_CHU", nullable = true)
	private String note;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project",orphanRemoval=true)
	@JsonBackReference
	private Set<Module> module;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project_workitem",orphanRemoval=true)
	@JsonBackReference
	private Set<WorkItem> workitem;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project_workdo",orphanRemoval=true)
	@JsonBackReference
	private Set<WorkDo> workdo;

}
