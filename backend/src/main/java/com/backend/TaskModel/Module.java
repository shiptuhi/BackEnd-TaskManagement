package com.backend.TaskModel;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import org.hibernate.annotations.Parameter;
@Entity
@Table(name = "MODULE")
@Data
public class Module {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "MA_MODULE", nullable = false)
	private String code;

	@Column(name = "TEN_MODULE", nullable = true)
	private String name;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DU_AN_ID", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private Project project;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "NHAN_VIEN_MODULE"
		, joinColumns = @JoinColumn(name = "MODULE_ID", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "NHAN_VIEN_ID", referencedColumnName = "id"))
	private List<Employee> emp;
	
	@JsonFormat(pattern="dd-MM-yyyy")
//	@DateTimeFormat(pattern= "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_BAT_DAU", nullable = true)
	private Date dateStart;

	@Column(name = "GHI_CHU", nullable = false)
	private String note;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "module_workitem",orphanRemoval=true)
	@JsonBackReference
	private Set<WorkItem> workitem;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "module_workdo",orphanRemoval=true)
	@JsonBackReference
	private Set<WorkDo> workdo;

}
