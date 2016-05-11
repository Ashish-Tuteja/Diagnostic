package com.auction.pro.listener;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.auction.pro.user.dto.UserLogDto;
import com.auction.pro.user.service.base.UserLoggingService;

public class LogoutSuccessListener implements LogoutSuccessHandler,
		LogoutHandler {
	@Autowired
	UserLoggingService userLoggingService;
	private static final Logger LOGGER = LoggerFactory.getLogger(LogoutSuccessListener.class
			.getName());

	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		//
		try {
			UserLogDto userLogDto = userLoggingService.save(new UserLogDto(
					authentication.getName(), request.getLocale()
							.getDisplayCountry(), "logout"));
			if (userLogDto.getId() != null) {
				LOGGER.info("Logout log update at" + userLogDto.getDate());

			} else {
				LOGGER.info("Logout log not update");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/?logout");

	}

	public void logout(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub

	}

}
