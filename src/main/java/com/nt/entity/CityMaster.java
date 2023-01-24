package com.nt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "city_master")
@Entity
@Data
public class CityMaster {
	
	@Id
	@Column(name="city_id")
	private Integer cityId;
	@Column(name="city_name")
	private String cityName;
	@Column(name="state_id")
	private Integer stateId;

}
