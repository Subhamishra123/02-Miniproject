package com.nt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.UserRegForm;
import com.nt.services.IUserMgmtService;

@RestController
public class RegistrationRestController {
	
	@Autowired
	private IUserMgmtService service;
	
	@GetMapping("/email/{emailId}")
	public String emailCheck(@PathVariable("emailId") String emailId) {
		return service.emailCheck(emailId);
	}
	
	@GetMapping("/countries")
	public Map<Integer, String> loadCountries()
	{
		return service.getCountries();
	}
	@GetMapping("/states/{countryId}")
	public Map<Integer, String> loadStates(@PathVariable("countryId") Integer countryId)
	{
		return service.getStates(countryId);
	}
	@GetMapping("/cities/{stateId}")
	public Map<Integer, String> loadCities(@PathVariable("stateId") Integer stateId)
	{
		return service.getCities(stateId);
	}
	
	@PostMapping("/user")
	public String register(@RequestBody UserRegForm form)
	{
		return service.register(form);
	}

}
