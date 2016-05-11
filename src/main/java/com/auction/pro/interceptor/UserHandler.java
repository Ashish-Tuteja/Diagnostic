package com.auction.pro.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.auction.pro.user.model.UserIdentity;

public class UserHandler implements HandlerInterceptor {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserHandler.class.getName());

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView mv)
			throws Exception {
		// TODO Auto-generated method stub
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		// TODO Auto-generated method stub
		String requestURL = null;
		try {
			requestURL = request.getRequestURL().toString();
			Authentication a = SecurityContextHolder.getContext()
					.getAuthentication();
			UserIdentity currentUser = (UserIdentity) a.getPrincipal();
			boolean userAuthorize = false;
			List<String> permissions = Arrays.asList("Device","Vehicle","Account","User","Import","EcuController");
			for (String permission : permissions) {
				if (requestURL.contains(permission.toLowerCase())
						|| requestURL.contains("home")) {
					userAuthorize = true;
					break;
				}
			}
			if (userAuthorize) {
				return userAuthorize;
			} else {
				response.sendRedirect(request.getContextPath() + "/404");
				return false;
			}

		} catch (ClassCastException e) {
			// TODO: handle exception
			LOGGER.info("RequestURl " + requestURL);
			if (requestURL.contains("/user/add")) {
				LOGGER.info("user add");
				return true;
			}
			LOGGER.error("User already Logout");

			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Unauthorized Access");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}

	}
}
