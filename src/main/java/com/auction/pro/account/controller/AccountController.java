package com.auction.pro.account.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.auction.pro.account.dto.AccountDto;
import com.auction.pro.account.service.base.AccountService;
import com.auction.pro.common.controller.AbstractController;
import com.auction.pro.common.utils.PagedRequest;
import com.auction.pro.email.EmailManager;
import com.auction.pro.user.service.base.UserService;

@Controller
@RequestMapping("/account") 
public class AccountController extends AbstractController {
	@Autowired
	AccountService service;
	@Autowired
	UserService userService;
	@Autowired
	EmailManager emailManager;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AccountController.class);

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	protected @ResponseBody AccountDto save(@RequestBody AccountDto accountDto,
			HttpServletRequest request) {
		try {
			if (!request.getSession(false).isNew()) {
				if (request.getSession(false).getAttribute("accountId") != null) {
					accountDto.setParentAccountId(request.getSession(false)
							.getAttribute("accountId").toString());
				} else {
					accountDto.setParentAccountId(service.findByUserId(
							currentUserNameByPrincipal().getId()).getId());
				}
			}
			// set userID for AccountUserMap
			accountDto.setUserId(currentUserNameByPrincipal().getId());
			AccountDto accountDtoResponse = service.save(accountDto);
			if (!accountDto.isStatus()) {
				try {
					if (currentUserNameByPrincipal().getEmailId() != null) {
						emailManager
								.sendMessage(
										"Activation Link",
										currentUserNameByPrincipal()
												.getEmailId(),
										"http://"
												+ request.getRemoteHost()
												+ ":8080/autoficio/activation/account"
												+ "/"
												+ accountDtoResponse
														.getActivationId());
						LOGGER.info("Email send to @ "
								+ currentUserNameByPrincipal().getEmailId());
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			return accountDtoResponse;
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("", e.getMessage());
			return null;
		}

	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public @ResponseBody List<AccountDto> getAccounts() {
		try {
			List<String> fields = new ArrayList<String>();
			fields.add("_id");
			fields.add("accountName");
			return service.findByParentAccountId(
					service.findByUserId(currentUserNameByPrincipal().getId())
							.getId(), fields);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("", e.getMessage());
			return null;
		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Page<AccountDto> getListofAccounts(
			PagedRequest pageRequest, HttpServletRequest request) {
		try {
			return service
					.findAllPage(
							pageRequest,
							request.getSession(false).getAttribute("accountId") != null ? request
									.getSession(false)
									.getAttribute("accountId").toString()
									: service.findByUserId(
											currentUserNameByPrincipal()
													.getId()).getId());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("", e.getMessage());
			return null;

		}

	}

	@RequestMapping(value = "/search/{searchterm}", method = RequestMethod.GET)
	public @ResponseBody List<AccountDto> getAccountBySearchTerm(
			@PathVariable String searchterm, HttpServletRequest request) {
		try {
			return service
					.findBySerachterm(
							searchterm,
							request.getSession(false).getAttribute("accountId") != null ? request
									.getSession(false)
									.getAttribute("accountId").toString()
									: service.findByUserId(
											currentUserNameByPrincipal()
													.getId()).getId());

		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("", e.getMessage());
			return null;
		}

	}
}
