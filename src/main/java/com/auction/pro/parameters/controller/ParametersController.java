package com.auction.pro.parameters.controller;

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
import com.auction.pro.parameters.dto.ParametersDto;
import com.auction.pro.parameters.service.base.ParametersService;

@Controller
@RequestMapping("/parameters")
public class ParametersController extends AbstractController {
	@Autowired
	ParametersService parametersService;
	@Autowired
	AccountService accountService;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ParametersController.class.getName());

	// Add/Update on parameter
	@RequestMapping(value = "/add/{controllerId}", method = RequestMethod.POST)
	protected @ResponseBody ParametersDto save(
			@RequestBody ParametersDto parametersDto,
			 @PathVariable String controllerId, HttpServletRequest request) {
		// TODO: handle exception
		try {
			LOGGER.info("paramerts id -------------->"+parametersDto.getId());
			
			if (parametersDto.getId() == null) {
				if (request.getSession(false).getAttribute("accountId") != null) {
					List<String> accountIDs = accountService.findById(
							request.getSession(false).getAttribute("accountId")
									.toString()).getParentAccountIds();
					accountIDs.add(request.getSession(false)
							.getAttribute("accountId").toString());
					parametersDto.setParentAccountId(accountIDs);
				} else {
					AccountDto accountDto = accountService
							.findByUserId(currentUserNameByPrincipal().getId());
					List<String> accountIds = accountDto.getParentAccountIds();
					accountIds.add(accountDto.getId());
					parametersDto.setParentAccountId(accountIds);
					
				}
			}
			
			parametersDto.setControllerId(controllerId);
			LOGGER.info("Paraent Account id --->"+ parametersDto.getParentAccountId() + "ParameterDto id ---->" + parametersDto.getId());
			return parametersService.save(parametersDto);
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
			LOGGER.info("Deleteing parameter for ::" + id);
			parametersService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Problem occured in deleting controller for " + id);
		}

	}
	
	// Delete Parameters on the basis of controller 
		@RequestMapping(value = "/deleteParamsForCtrl/{id}", method = RequestMethod.DELETE, produces = "application/json")
		protected @ResponseBody void DeleteForCtrl(@PathVariable String id) {
			// TODO: handle exception

			try {
				LOGGER.info("Deleteing all parameters for controller ID::" + id);
				parametersService.deleteParams(id);
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("Problem occured in deleting parameters for controller " + id);
			}

		}

	// List all parameters
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Page<ParametersDto> getListofParameters(
			PagedRequest pageRequest, HttpServletRequest request) {
		try {
			
			return parametersService
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
			LOGGER.error("Problem occur get parameters", e.getMessage());
			return null;
		}

	}

	
	
	
	// List parameters By Controller id
		@RequestMapping(value = "/list/{controllerId}", method = RequestMethod.GET)
		public @ResponseBody Page<ParametersDto> getListofParametersByControllerId(
				PagedRequest pageRequest,@PathVariable String controllerId, HttpServletRequest request) {
			try {
				
				return parametersService
						.findAllPageParameters(controllerId,
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
				LOGGER.error("Problem occur get parameters", e.getMessage());
				return null;
			}

		}


	// Search on list of parameters
	@RequestMapping(value = "/search/{searchterm}/{contId}", method = RequestMethod.GET)
	public @ResponseBody List<ParametersDto> getparametersBySearchTerm(
			@PathVariable String searchterm,@PathVariable String contId, HttpServletRequest request) {
		LOGGER.info("Search term " + searchterm);
		LOGGER.info("controller ID " + contId);
		try {
			return parametersService
					.findBySerachterm(
							contId,
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
