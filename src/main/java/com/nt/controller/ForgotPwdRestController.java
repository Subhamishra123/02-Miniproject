package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nt.services.IUserMgmtService;

@RestController
public class ForgotPwdRestController {
	
	@Autowired
	private IUserMgmtService service;
	
	@GetMapping("/forgotPwd/{emailId}")
	public String forgotPassword(@PathVariable("emailId") String emailId) {
		return service.forgotPassword(emailId);
	}

}
