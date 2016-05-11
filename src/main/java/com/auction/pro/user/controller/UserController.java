package com.auction.pro.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
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

import com.auction.pro.account.service.base.AccountService;
import com.auction.pro.common.controller.AbstractController;
import com.auction.pro.common.utils.CommonUtils;
import com.auction.pro.common.utils.PagedRequest;
import com.auction.pro.email.EmailManager;
import com.auction.pro.user.dto.RoleDto;
import com.auction.pro.user.dto.UserDto;
import com.auction.pro.user.service.base.UserAccountMapService;
import com.auction.pro.user.service.base.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {
	@Autowired
	UserService userService;
	@Autowired
	AccountService accountService;
	@Autowired
	UserAccountMapService userAccountmapService;
	@Autowired
	EmailManager emailManager;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserController.class);

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody UserDto saveUser(@RequestBody UserDto user,
			HttpServletRequest request) {
		if (!request.getSession(false).isNew()) {
			LOGGER.debug("Session not new ");
			if (request.getSession(false).getAttribute("accountId") != null) {
				user.setParentAccountId(request.getSession(false)
						.getAttribute("accountId").toString());
			} else {
				try {
					user.setParentAccountId(accountService.findByUserId(
							currentUserNameByPrincipal().getId()).getId());
				} catch (Exception e) {
					// TODO: handle exception
					LOGGER.error("Admin created");
				}

			}
		}
		try {
			UserDto saveUser = userService.save(user);
			// emailActivation
			if (user.getId() == null) {
				try {
					if (saveUser != null) {
						emailManager
								.sendMessage(
										"Activation Link",
										saveUser.getEmailId(),
										"http://"
												+ request.getRemoteHost()
												+ ":8080/autoficio/activation/account"
												+ "/"
												+ saveUser
														.getAccountActivationId());
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					LOGGER.error("Email not send ");
				}
			}
			if (saveUser == null) {
				return user;
			} else {
				return saveUser;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Page<UserDto> listUsers(PagedRequest pageable,
			HttpServletRequest request) {
		try {
			List<String> fields = new ArrayList<String>();
			fields.add("_id");
			return userService
					.findAllPage(
							pageable,
							request.getSession(false).getAttribute("accountId") != null ? userAccountmapService
									.findUserIdByAccountId(accountService
											.findByParentAccountId(request
													.getSession(false)
													.getAttribute("accountId")
													.toString(), fields))
									: userAccountmapService
											.findUserIdByAccountId(accountService
													.findByParentAccountId(
															accountService
																	.findByUserId(
																			currentUserNameByPrincipal()
																					.getId())
																	.getId(),
															fields)));
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
			return null;
		}

	}

	@RequestMapping(value = "/search/{searchterm}", method = RequestMethod.GET)
	public @ResponseBody List<UserDto> findBySearchTerm(
			@PathVariable String searchterm, HttpServletRequest request) {
		try {
			List<String> fields = new ArrayList<String>();
			fields.add("_id");
			return userService
					.findBySerachterm(
							searchterm,
							request.getSession(false).getAttribute("accountId") != null ? userAccountmapService
									.findUserIdByAccountId(accountService
											.findByParentAccountId(request
													.getSession(false)
													.getAttribute("accountId")
													.toString(), fields))
									: userAccountmapService
											.findUserIdByAccountId(accountService
													.findByParentAccountId(
															accountService
																	.findByUserId(
																			currentUserNameByPrincipal()
																					.getId())
																	.getId(),
															fields)));
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
			return null;
		}

	}

	@RequestMapping(value = "/getuseraccount", method = RequestMethod.GET)
	public @ResponseBody UserDto getUserandAccount() {
		try {
			UserDto userDto = userService.findById(currentUserNameByPrincipal()
					.getId());
			userDto.setAddress(userService.findContactById(userDto
					.getContactId()));
			return userDto;
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
			return null;
		}

	}

	@RequestMapping(value = "/accountsetting", method = RequestMethod.GET)
	public @ResponseBody String sendCodeToChangeAccount(
			HttpServletRequest request) {
		try {
			String uuId = String.valueOf(UUID.randomUUID()).substring(1, 5);
			emailManager.sendMessage("Change Password",
					currentUserNameByPrincipal().getEmailId(), uuId);
			request.getSession(false).setAttribute("confirmcode", uuId);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public @ResponseBody UserDto changePassword(@RequestBody UserDto userDTOs,
			HttpServletRequest request) {
		userDTOs.setId(currentUserNameByPrincipal().getId());
		try {
			if (request.getSession(false).getAttribute("confirmcode")
					.equals(userDTOs.getPasswordCode())) {
				userDTOs.setPassword(CommonUtils.encryption(userDTOs
						.getPassword()));
				userService.updatePassword(userDTOs);
				LOGGER.info("Change Password ");
				request.getSession(false).removeAttribute("confirmcode");
				userDTOs.setPassword("");
				userDTOs.setCnfPassword("");
				return userDTOs;
			} else {
				request.getSession(false).removeAttribute("confirmcode");
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
			return null;
		}

	}

	@RequestMapping(value = "/roles/list", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<RoleDto> listRoles() {
		try {
			return userService.getAllRoles();
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
			return null;
		}

	}
}
