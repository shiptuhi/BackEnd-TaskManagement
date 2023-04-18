package com.backend.TaskModel;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Table(name = "CONG_VIEC")
@Data
public class WorkDo {

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "MA_CONG_VIEC", nullable = false)
	private String code;

	@Column(name = "TEN_CONG_VIEC", nullable = false)
	private String name;

	@ManyToOne()
	@JoinColumn(name = "DAU_MUC_ID", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private WorkItem workItem;

	@ManyToOne()
	@JoinColumn(name = "MODULE_ID", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private Module module_workdo;

	@ManyToOne()
	@JoinColumn(name = "DU_AN_ID", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private Project project_workdo;

	@ManyToOne()
	@JoinColumn(name = "LOAI_CONG_VIEC", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private TypesofWork type;

	@ManyToOne()
	@JoinColumn(name = "MUC_UU_TIEN", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private WorkPriority priority;

	@ManyToOne()
	@JoinColumn(name = "NHOM_CONG_VIEC", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private GroupofWork group;
	
//	@ManyToOne()
//	@JoinColumn(name = "NGUOI_THUC_HIEN", nullable = false)
//	@JsonIgnore
//	@JsonManagedReference
//	private Employee emp1;
//	
//	@ManyToOne()
//	@JoinColumn(name = "NGUOI_QUAN_LY", nullable = false)
//	@JsonIgnore
//	@JsonManagedReference
//	private Employee emp2;


//	@ManyToOne()
//	@JoinColumn(name = "KHACH_HANG_ID", nullable = false)
//	@JsonIgnore
//	@JsonManagedReference
//	private Customer customer_workdo;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "DU_KIEN_BAT_DAU", nullable = true)
	private Date expDateStart;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "DU_KIEN_KET_THUC", nullable = true)
	private Date expDateEnd;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "NGAY_BAT_DAU", nullable = true)
	private Date dateStart;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "NGAY_KET_THUC", nullable = true)
	private Date dateEnd;

	@Column(name = "NOI_DUNG", nullable = true)
	private String content;

	@ManyToOne()
	@JoinColumn(name = "TRANG_THAI", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private WorkStatus status_workdo;

	@Column(name = "GHI_CHU", nullable = true)
	private String note;

	@Column(name = "CONG_VIEC_OT", nullable = false)
	private Overtime ot;

	public enum Overtime {
		No, Yes;
	}

}
