package com.auction.pro.user.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import com.auction.pro.user.dao.base.UserLoginDao;

@Service(value = "userLoginServiceFailure")
public class UserLoginServiceFailure implements AuthenticationFailureHandler,
		ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
	@Autowired
	UserLoginDao userLoginDao;

	public UserLoginServiceFailure() {
		// TODO Auto-generated constructor stub
	}

	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.sendRedirect(request.getContextPath() + "/?error");
	}

	public void onApplicationEvent(
			AuthenticationFailureBadCredentialsEvent event) {
		// TODO Auto-generated method stub
		// userLoginDao.enableLoginAttempts(String.valueOf(event.getAuthentication().getPrincipal()));

	}

}
