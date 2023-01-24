package com.nt.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserRegForm {
	
	
	private String fname;
	private String lname;
	private String email;
	private Integer phno;
	private LocalDate dob;
	private String gender;
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;
	
	
}
