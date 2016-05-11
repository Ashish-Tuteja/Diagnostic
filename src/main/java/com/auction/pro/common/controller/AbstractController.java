package com.auction.pro.common.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.auction.pro.user.model.UserIdentity;

@Component
public class AbstractController {

	public UserIdentity currentUserNameByPrincipal() {
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

}
