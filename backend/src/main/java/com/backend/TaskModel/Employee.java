package com.backend.TaskModel;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
		Female, Male, Undefined;
	}

	@Column(name = "PHONG_BAN", nullable = false)
	private String department;

	@Column(name = "TRANG_THAI", nullable = false)
	private Status status;

	public enum Status {
		Inactive, Active;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "NHAN_VIEN_CHUC_VU"
		, joinColumns = @JoinColumn(name = "NHAN_VIEN_ID", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "CHUC_VU_ID", referencedColumnName = "id"))
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "ID", referencedColumnName = "id")
	private Set<Role> role = new HashSet<>();
}