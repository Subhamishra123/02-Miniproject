package com.nt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "state_master")
@Entity
@Data
public class StateMaster {
	
	@Id
	@Column(name="state_id")
	private Integer stateId;
	@Column(name="state_name")
	private String stateName;
	@Column(name="country_id")
	private Integer countryId;

}
