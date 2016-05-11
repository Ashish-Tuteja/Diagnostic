package com.auction.pro.aspect;

import java.util.Locale;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.auction.pro.user.dto.UserLogDto;
import com.auction.pro.user.model.UserIdentity;
import com.auction.pro.user.service.base.UserLoggingService;

@Aspect
public class LogsAspect {
	@Autowired
	UserLoggingService userLoggingService;

	@After("execution(* com.auction.pro.common.dao.AbstractDAOImpl.save(..))")
	public void logAfter(JoinPoint joinPoint) {
		if (joinPoint.getArgs()[0].getClass().getSimpleName().equals("User")) {
			saveLog("Create/Update user");

		} else if (joinPoint.getArgs()[0].getClass().getSimpleName()
				.equals("Account")) {
			saveLog("Create/Update account");
		}

	}

	public UserIdentity currentUserByPrincipal() {
		try {
			Authentication a = SecurityContextHolder.getContext()
					.getAuthentication();
			UserIdentity currentUser = (UserIdentity) a.getPrincipal();
			return currentUser;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	public boolean saveLog(String activity) {
		if (currentUserByPrincipal() != null) {
			userLoggingService.save(new UserLogDto(currentUserByPrincipal()
					.getUsername(), Locale.getDefault().getDisplayCountry(),
					activity));
			return true;
		}
		return false;

	}
}
