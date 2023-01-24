package com.nt.services;

import java.util.Map;

import com.nt.bindings.LoginForm;
import com.nt.bindings.UnlockAccForm;
import com.nt.bindings.UserRegForm;

public interface IUserMgmtService {

	String login(LoginForm form);
	String emailCheck(String email);
	String register(UserRegForm form);
	Map<Integer,String> getCountries();
	Map<Integer,String> getStates(Integer countryId);
	Map<Integer,String> getCities(Integer stateId);
	String accountUnlock(UnlockAccForm form);
	String forgotPassword(String email);
}
