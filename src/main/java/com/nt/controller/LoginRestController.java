package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.LoginForm;
import com.nt.services.IUserMgmtService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginRestController {
	
	@Autowired
	private IUserMgmtService service;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm form) {
		return service.login(form);
	}

}
