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
@Table(name = "DAU_MUC")
@Data
public class WorkItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "MA_DAU_MUC", nullable = false)
	private String code;

	@Column(name = "TEN_DAU_MUC", nullable = true)
	private String name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MODULE_ID", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private Module module_workitem;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DU_AN_ID", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private Project project_workitem;

	@Column(name = "MUC_UU_TIEN", nullable = false)
	private WorkPriority priority;
	
	public enum WorkPriority {
		Emergency, High, Medium, Low;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "NGUOI_THUC_HIEN", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private Employee emp_workitem_1;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "NGUOI_QUAN_LY", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private Employee emp_workitem_2;

	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name = "NGAY_BAT_DAU", nullable = true)
	private Date dateStart;

	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name = "NGAY_KET_THUC", nullable = true)
	private Date dateEnd;

	@Column(name = "NOI_DUNG", nullable = true)
	private String content;

	@Column(name = "TRANG_THAI", nullable = false)
	private WorkStatus status;
	
	public enum WorkStatus {
		Finish, Processing, Pending, Pause, Cancel;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "workItem",orphanRemoval=true)
	@JsonBackReference
	private Set<WorkDo> workdo;

}
