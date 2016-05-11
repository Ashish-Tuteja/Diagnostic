package com.auction.pro.email;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MailTemplateFactory {

	private static final String TEMPLATE_PATH = "resources/";

	public static StringBuffer getWelcomeMessageEmailFormat(String firstName,
			String username, String loginUrl, String confirmationUrl) {
		String s = htmlTemplateToStringBuffer("welcome-message.html")
				.toString().replaceAll("##FIRSTNAME##", firstName);
		return new StringBuffer(s);
	}

	private static StringBuffer htmlTemplateToStringBuffer(String templateName) {

		StringBuffer sb = new StringBuffer();
		InputStream stream = MailTemplateFactory.class
				.getResourceAsStream(TEMPLATE_PATH + templateName);
		BufferedReader in = new BufferedReader(new InputStreamReader(stream));
		try {
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return sb;

	}
}
