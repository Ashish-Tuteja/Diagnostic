package com.auction.pro.dashboard.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.auction.pro.account.dto.AccountDto;
import com.auction.pro.account.service.base.AccountService;
import com.auction.pro.common.controller.AbstractController;
import com.auction.pro.common.utils.PagedRequest;
import com.auction.pro.user.dto.UserLogDto;
import com.auction.pro.user.service.base.UserLoggingService;
import com.auction.pro.user.service.base.UserService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends AbstractController {
	@Autowired
	AccountService accountService;
	@Autowired
	UserService userService;
	@Autowired
	UserLoggingService userLogService;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DashboardController.class);

	@RequestMapping(value = "/getAccounts", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<AccountDto> getAccounts() {
		try {
			List<String> fields = new ArrayList<String>();
			fields.add("_id");
			fields.add("accountName");
			fields.add("status");
			return accountService.findByParentAccountId(
					accountService.findByUserId(
							currentUserNameByPrincipal().getId()).getId(),
					fields);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("", e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/accountsession/{accountID}", method = RequestMethod.GET)
	public @ResponseBody String accountSession(@PathVariable String accountID,
			HttpServletRequest request) {
		try {
			request.getSession(false).setAttribute("accountId", accountID);
			return "{\"message\":\"Account Changed\"}";

		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("", e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/getUserLogs", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Page<UserLogDto> getUserLog(PagedRequest pageable) {
		try {
			return userLogService.findAllPage(pageable);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("", e.getMessage());
			return null;
		}

	}
}
