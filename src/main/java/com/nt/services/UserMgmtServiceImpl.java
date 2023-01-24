package com.nt.services;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.nt.bindings.LoginForm;
import com.nt.bindings.UnlockAccForm;
import com.nt.bindings.UserRegForm;
import com.nt.entity.CityMaster;
import com.nt.entity.CountryMaster;
import com.nt.entity.StateMaster;
import com.nt.entity.UserMaster;
import com.nt.repo.ICityRepo;
import com.nt.repo.ICountryRepo;
import com.nt.repo.IStateRepo;
import com.nt.repo.IUserRepo;
import com.nt.utils.EmailUtils;

@Service
public class UserMgmtServiceImpl implements IUserMgmtService {

	@Autowired
	private IUserRepo userRepo;

	@Autowired
	private ICountryRepo countryRepo;

	@Autowired
	private IStateRepo stateRepo;

	@Autowired
	private ICityRepo cityRepo;

	@Autowired
	private EmailUtils emailUtil;

	public String login(LoginForm form) {
		String email = form.getEmail();
		String password = form.getPassword();
		Optional<UserMaster> optinalObj = userRepo.findByEmailAndPassword(email, password);
		if (!optinalObj.isPresent()) {
			return "Invalid Credentials";
		}
		UserMaster userMaster = optinalObj.get();
		if (userMaster != null && userMaster.getIsAccountLocked()) {
			return "Your Account is Locked";
		}

		return "Success";
	}

	public String emailCheck(String email) {
		Optional<UserMaster> findByEmail = userRepo.findByEmail(email);
		if (findByEmail.isPresent()) {
			return "DUPLICATE";
		}
		return "UNIQUE";
	}

	public String register(UserRegForm form) {
		UserMaster user = new UserMaster();
		BeanUtils.copyProperties(form, user);
		user.setPassword(generatePassword());
		user.setIsAccountLocked(true);
		UserMaster save = userRepo.save(user);
		String email = form.getEmail();
		String subject = "Unlock User Account";
		String filename = "unlock_accnt_by_email.txt";
		String body = generateBody(filename, user);
		boolean sendMail = emailUtil.sendMail(email, subject, body);
		if (sendMail)
			return "Success";
		return "Fail";
	}

	public Map<Integer, String> getCountries() {
		return countryRepo.findAll().stream()
				.collect(Collectors.toMap(CountryMaster::getCountryId, CountryMaster::getCountryName));

	}

	public Map<Integer, String> getStates(Integer countryId) {

		return stateRepo.findByCountryId(countryId).stream()
				.collect(Collectors.toMap(StateMaster::getStateId, StateMaster::getStateName));

	}

	public Map<Integer, String> getCities(Integer stateId) {

		return cityRepo.findByStateId(stateId).stream()
				.collect(Collectors.toMap(CityMaster::getCityId, CityMaster::getCityName));
	}

	public String accountUnlock(UnlockAccForm form) {
		String email = form.getEmail();
		Optional<UserMaster> findByEmail = userRepo.findByEmail(email);
		if (findByEmail.isPresent()) {
			UserMaster userMaster = findByEmail.get();
			if (userMaster.getPassword().equalsIgnoreCase(form.getTempPwd())
					&& form.getNewPwd().equalsIgnoreCase(form.getConfirmPwd())) {

				userMaster.setPassword(form.getNewPwd());
				userMaster.setIsAccountLocked(false);
				userRepo.save(userMaster);
				return "Success";
			}
		}
		return "Failure";
	}

	public String forgotPassword(String email) {
		Optional<UserMaster> findByEmail = userRepo.findByEmail(email);
		if (findByEmail.isPresent()) {
			// String password = findByEmail.get().getPassword();
			String subject = "User Account Password";
			String mailBody = generateBody("forgot_pwd", findByEmail.get());
			boolean sendMail = emailUtil.sendMail(email, subject, mailBody);
			if (sendMail) {
				return "Success";
			}
		}
		return "Failure";
	}

	private String generatePassword() {

		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();

		return generatedString;
	}

	private String generateBody(String fileName, UserMaster user) {
		String mailBody = null;
		try {
			Resource resource = new ClassPathResource(fileName);

			File file = resource.getFile();
			StringBuffer sb = new StringBuffer();

			FileReader fr = new FileReader(file);

			// FileReader fr=new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			mailBody = sb.toString();
			mailBody = mailBody.replace("{FNAME}", user.getFname());
			mailBody = mailBody.replace("{LNAME}", user.getLname());
			mailBody = mailBody.replace("{TEMP-PWD}", user.getPassword());
			mailBody = mailBody.replace("{EMAIL}", user.getEmail());
			mailBody = mailBody.replace("{PWD}", user.getPassword());
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}

}
