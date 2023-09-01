package com.example.authDemo.core.utils.email;




import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailManager implements EmailSender { //Java mail sender kullanacağız

	@Autowired
	private final static Logger LOGGER = LoggerFactory.getLogger(EmailManager.class);
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	@Async
	public void send(String to, String email) {
		mailSender = new JavaMailSenderImpl();
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
			helper.setText(email,true);
			helper.setTo(to);
			helper.setSubject("Confirm your email");
			helper.setFrom("hello@example.com");
		} catch (MessagingException e) {
			LOGGER.error("failed to send email",e);
			throw new IllegalStateException("failed to send email");
		}
		
	}

}
