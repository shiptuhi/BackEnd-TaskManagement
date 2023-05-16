package com.backend.TaskModel;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

@Entity
@Table(name = "CONG_VIEC")
@Data
public class WorkDo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "MA_CONG_VIEC", nullable = false)
	private String code;

	@Column(name = "TEN_CONG_VIEC", nullable = false)
	private String name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DAU_MUC_ID", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private WorkItem workItem;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MODULE_ID", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private Module module_workdo;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DU_AN_ID", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private Project project_workdo;

	@Column(name = "LOAI_CONG_VIEC", nullable = false)
	private TypesofWork type;
	
	public enum TypesofWork{
		Complex, Medium, Simple;
	}

	@Column(name = "MUC_UU_TIEN", nullable = false)
	private WorkPriority priority;
	
	public enum WorkPriority {
		Emergency, High, Medium, Low;
	}

	@Column(name = "NHOM_CONG_VIEC", nullable = false)
	private GroupofWork group;
	
	public enum GroupofWork{
		New, Fix;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "NGUOI_THUC_HIEN", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private Employee emp_workdo_3;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "NGUOI_QUAN_LY", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private Employee emp_workdo_4;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "NGAY_BAT_DAU", nullable = true)
	private Date dateStart;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "NGAY_KET_THUC", nullable = true)
	private Date dateEnd;

	@Column(name = "NOI_DUNG", nullable = true)
	private String content;

	@Column(name = "TRANG_THAI", nullable = false)
	private WorkStatus status;
	
	public enum WorkStatus {
		Finish, Processing, Pending, Pause, Cancel;
	}

	@Column(name = "GHI_CHU", nullable = true)
	private String note;

	@Column(name = "CONG_VIEC_OT", nullable = false)
	private Overtime ot;

	public enum Overtime {
		No, Yes;
	}

}
