package com.auction.pro.controller;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.auction.pro.account.service.base.AccountService;
import com.auction.pro.common.controller.AbstractController;
import com.auction.pro.user.model.User;
import com.auction.pro.user.model.UserIdentity;
import com.auction.pro.user.service.base.UserService;

/**
 * Handles and retrieves the login or denied page depending on the URI template
 */
@Controller
public class LoginController extends AbstractController {
	@Autowired
	UserService userService;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(LoginController.class.getName());
	User user = new User();
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	AccountService accountService;

	@RequestMapping(value = "/")
	public ModelAndView basePage(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout)
			throws IOException {

		LOGGER.info("LoginPage is executed! in  /");
		try {
			Authentication a = SecurityContextHolder.getContext()
					.getAuthentication();
			@SuppressWarnings("unused")
			UserIdentity currentUser = (UserIdentity) a.getPrincipal();
			return new ModelAndView("redirect:/home");

		} catch (Exception e) {
			ModelAndView model = new ModelAndView();
			if (error != null) {
				model.addObject("error", "Invalid username and password!");
			}

			if (logout != null) {
				model.addObject("msg", "You've been logged out successfully.");
			}
			model.setViewName("/app");

			return model;

		}

	}

	@RequestMapping(value = "/home")
	public ModelAndView dashboard() throws JSONException {
		int count = 0;
		JSONObject responseJSON = new JSONObject();
		Authentication a = SecurityContextHolder.getContext()
				.getAuthentication();
		UserIdentity currentUser = (UserIdentity) a.getPrincipal();
		try {
			userService
					.disableLoginAttempts(currentUser.getUsername() == null ? currentUser
							.getEmailId() : currentUser.getUsername());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("", e.getMessage());
		}
		LOGGER.info("Success Login");
		List<String> permissions = currentUser.getRolePermission();
		for (String name : permissions) {
			responseJSON.put("permission" + count++, name);

		}
		ModelAndView dashboard = new ModelAndView("dashboard");
		dashboard.addObject("permissions", responseJSON);
		return dashboard;
	}

	@RequestMapping("/activation/account/{activationId}")
	public ModelAndView activation(@PathVariable String activationId) {
		try {
			accountService.activation(activationId);
			LOGGER.info("Activation Complete...............");
		} catch (Exception e) {
			LOGGER.error("Activation not complete");
		}
		if (currentUserNameByPrincipal() == null) {
			return new ModelAndView("redirect:/");
		} else {
			return new ModelAndView("redirect:/home");
		}

	}

}
