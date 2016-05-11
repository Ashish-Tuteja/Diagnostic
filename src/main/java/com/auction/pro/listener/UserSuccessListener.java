package com.auction.pro.listener;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.auction.pro.user.dto.UserLogDto;
import com.auction.pro.user.model.UserIdentity;
import com.auction.pro.user.service.base.UserLoggingService;

public class UserSuccessListener implements AuthenticationSuccessHandler {

	@Autowired
	UserLoggingService userLoggingService;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserSuccessListener.class.getName());

	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		Authentication a = SecurityContextHolder.getContext()
				.getAuthentication();

		UserIdentity currentUser = (UserIdentity) a.getPrincipal();
		String user = currentUser.getUsername() == null ? currentUser
				.getEmailId() : currentUser.getUsername();
		UserLogDto userLogDto = userLoggingService.save(new UserLogDto(user,
				request.getLocale().getDisplayCountry(), "login"));
		if (userLogDto.getId() != null) {
			LOGGER.info("Login log update " + userLogDto.getDate());

		} else {
			LOGGER.info("Login log not update");
		}
		response.sendRedirect(request.getContextPath() + "/home");

	}
}
