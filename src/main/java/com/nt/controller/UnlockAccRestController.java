package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.UnlockAccForm;
import com.nt.services.IUserMgmtService;

@RestController
public class UnlockAccRestController {
	
	@Autowired
	private IUserMgmtService service;
	
	@PostMapping("/unlockAccount")
	public String accountUnlock(@RequestBody UnlockAccForm form) {
		return service.accountUnlock(form);
	}

}
