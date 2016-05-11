package com.auction.pro.email;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.auction.pro.common.utils.CommonUtils;

@Component(value = "emailManager")
public class EmailManager {
	String smtpHost;
	String smtpUsername;
	String smtpPassword;
	private String subject;
	private String recipientEmail;
	private String message;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EmailManager.class.getName());

	public EmailManager() {
	}

	public EmailManager(String subject, String recipientEmail, String message) {
		this.subject = subject;
		this.recipientEmail = recipientEmail;
		this.message = message;
	}

	private JavaMailSenderImpl getMailSender() {
		try {
			Properties prop = CommonUtils.getProperty("smtp.properties");
			smtpHost = prop.getProperty("smtp.host");
			smtpUsername = prop.getProperty("smtp.username");
			smtpPassword = prop.getProperty("smtp.password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost(smtpHost);
		javaMailSenderImpl.setPort(587);
		javaMailSenderImpl.setUsername(smtpUsername);
		javaMailSenderImpl.setPassword(smtpPassword);
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", true);
		properties.put("mail.debug", true);
		javaMailSenderImpl.setJavaMailProperties(properties);
		return javaMailSenderImpl;

	}

	public boolean sendMessage() throws MessagingException {

		try {
			JavaMailSenderImpl javaMailSenderImpl = getMailSender();

			MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();

			MimeMessageHelper msg = new MimeMessageHelper(mimeMessage, true);
			msg.setFrom("sent from");
			msg.setTo(recipientEmail);
			msg.setSubject(subject);
			msg.setText(message, true);
			javaMailSenderImpl.send(mimeMessage);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MailSendException(
					"Unable to connect with SMTP server.Please try after some time.");
		}
	}

	public void sendMessage(String subject, String recipientEmail,
			String message) throws MessagingException {
		String to = recipientEmail;
		String from = "rghvj.11@gmail.com";
		Properties properties = System.getProperties();
		Session session = Session.getDefaultInstance(properties);
		try {
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(from));
			mimeMessage.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(message);
			Transport.send(mimeMessage);
			LOGGER.info("Email send @" + recipientEmail);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public void sendMessage(String subject, String[] recipientEmails,
			String message) throws MessagingException {

		JavaMailSenderImpl javaMailSenderImpl = getMailSender();

		for (int i = 0; i < recipientEmails.length; i++) {
			MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
			MimeMessageHelper msg = new MimeMessageHelper(mimeMessage, true);
			msg.setFrom(smtpUsername);
			msg.setTo(recipientEmails[i]);
			msg.setSubject(subject);
			msg.setText(message, true);
			javaMailSenderImpl.send(mimeMessage);
		}

	}
}
