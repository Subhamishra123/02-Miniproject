package com.nt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "country_master")
@Entity
@Data
public class CountryMaster {
	
	@Id
	@Column(name="country_id")
	private Integer countryId;
	@Column(name="country_name")
	private String countryName;

}
