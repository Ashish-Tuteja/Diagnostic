package com.auction.pro.listener;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.auction.pro.user.dao.base.UserLoginDao;

public class UserFailureListener implements AuthenticationFailureHandler,
		ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
	@Autowired
	UserLoginDao userLoginDao;

	public UserFailureListener() {
		// TODO Auto-generated constructor stub
	}

	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		userLoginDao.enableLoginAttempts(String.valueOf(request
				.getParameter("j_username")));
		response.sendRedirect(request.getContextPath() + "/?error");
	}

	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent arg0) {
		// TODO Auto-generated method stub

	}
}
