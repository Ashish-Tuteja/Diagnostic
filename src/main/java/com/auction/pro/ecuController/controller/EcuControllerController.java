package com.auction.pro.ecuController.controller;

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
import com.auction.pro.ecuController.dto.EcuControllerDto;
import com.auction.pro.ecuController.service.base.EcuControllerService;

@Controller
@RequestMapping("/ecuController")
public class EcuControllerController extends AbstractController {
	@Autowired
	EcuControllerService ecuControllerService;
	@Autowired
	AccountService accountService;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EcuControllerController.class.getName());

	// Add/Update on getId for controller
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	protected @ResponseBody EcuControllerDto save(
			@RequestBody EcuControllerDto ecuControllerDto,
			HttpServletRequest request) {
		// TODO: handle exception
		try {
			System.out.println(ecuControllerDto.getId());
			if (ecuControllerDto.getId() == null) {
				if (request.getSession(false).getAttribute("accountId") != null) {
					System.out.println("first");

					List<String> accountIDs = accountService.findById(
							request.getSession(false).getAttribute("accountId")
									.toString()).getParentAccountIds();
					accountIDs.add(request.getSession(false)
							.getAttribute("accountId").toString());
					ecuControllerDto.setParentAccountId(accountIDs);
				} else {
					AccountDto accountDto = accountService
							.findByUserId(currentUserNameByPrincipal().getId());
					List<String> accountIds = accountDto.getParentAccountIds();
					accountIds.add(accountDto.getId());
					ecuControllerDto.setParentAccountId(accountIds);
				}
			}
			return ecuControllerService.save(ecuControllerDto);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}


	// Delete Functionality By id
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	protected @ResponseBody void DeleteById(@PathVariable String id) {
		// TODO: handle exception

		try {
			System.out.println("Deleteing Controllers for ::" + id);
			LOGGER.info("Deleteing Controllers for ::" + id);
			ecuControllerService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Problem occured in deleting controller for " + id);
		}

	}

	// List all controllers
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Page<EcuControllerDto> getListofEcuControllers(
			PagedRequest pageRequest, HttpServletRequest request) {
		try {
			
			return ecuControllerService
					.findAllPage(
							pageRequest,
							request.getSession(false).getAttribute("accountId") != null ? request
									.getSession(false)
									.getAttribute("accountId").toString()
									: accountService.findByUserId(
											currentUserNameByPrincipal()
													.getId()).getId());

		} catch (Exception e) {
			e.printStackTrace();

			// TODO: handle exception
			LOGGER.error("Problem occur get controllers", e.getMessage());
			return null;
		}

	}

	
	
	// Search on list of Controllers
	@RequestMapping(value = "/search/{searchterm}", method = RequestMethod.GET)
	public @ResponseBody List<EcuControllerDto> getEcuControllerBySearchTerm(
			@PathVariable String searchterm, HttpServletRequest request) {
		LOGGER.info("Search term " + searchterm);
		try {
			return ecuControllerService
					.findBySerachterm(
							searchterm,
							request.getSession(false).getAttribute("accountId") != null ? request
									.getSession(false)
									.getAttribute("accountId").toString()
									: accountService.findByUserId(
											currentUserNameByPrincipal()
													.getId()).getId());

		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Search not work properly on " + searchterm,
					e.getMessage());
			return null;
		}
	}
}
