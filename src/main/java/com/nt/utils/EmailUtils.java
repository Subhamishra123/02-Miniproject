package com.nt.utils;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender sender;
	boolean isSent=false;
	public boolean sendMail(String to,String subject,String body) {
		try {
			MimeMessage mimeMessage = sender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(body, true);
			mimeMessageHelper.setTo(to);
			sender.send(mimeMessage);
			isSent=true;
		}
		catch(Exception e) {
			isSent=false;
			e.printStackTrace();
		}
		return isSent;
	}
}
