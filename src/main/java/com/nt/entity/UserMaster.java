package com.nt.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="user_mgmt")
public class UserMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer userId;
	@Column(name="user_fname")
	private String fname;
	@Column(name="user_lname")
	private String lname;
	@Column(name="user_email",unique = true)
	private String email;
	@Column(name="user_phno")
	private Integer phno;
	@Column(name="user_dob")
	private LocalDate date;
	@Column(name="user_gnder")
	private String gender;
	@Column(name="country_id")
	private Integer countryId;
	@Column(name="state_id")
	private Integer stateId;
	@Column(name="city_id")
	private Integer cityId;
	@Column(name="user_pwd")
	private String password=null;
	@Column(name="user_acc_status")
	private Boolean isAccountLocked=true;
	@Column(name="created_date",updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;
	@Column(name = "updated_date",insertable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;

}
