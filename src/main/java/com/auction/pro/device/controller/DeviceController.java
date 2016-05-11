package com.auction.pro.device.controller;

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
import com.auction.pro.device.dto.CarrierDto;
import com.auction.pro.device.dto.DeviceDto;
import com.auction.pro.device.dto.DeviceTypeDto;
import com.auction.pro.device.service.base.DeviceService;

@Controller
@RequestMapping("/device") 
public class DeviceController extends AbstractController {
	@Autowired
	DeviceService deviceService;
	@Autowired
	AccountService accountService;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DeviceController.class.getName());

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	protected @ResponseBody DeviceDto save(@RequestBody DeviceDto deviceDto,
			HttpServletRequest request) {
		// TODO: handle exception
		try {
			if (deviceDto.getId() == null) {
				System.out.println("Device Dtos id::::::"+deviceDto.getId());
				if (request.getSession(false).getAttribute("accountId") != null) {

					List<String> accountIDs = accountService.findById(
							request.getSession(false).getAttribute("accountId")
									.toString()).getParentAccountIds();
					System.out.println("Account Ids :::::::"+accountIDs);
					accountIDs.add(request.getSession(false)
							.getAttribute("accountId").toString());
					deviceDto.setParentAccountId(accountIDs);
				} else {
					AccountDto accountDto = accountService
							.findByUserId(currentUserNameByPrincipal().getId());
					List<String> accountIds = accountDto.getParentAccountIds();
					System.out.println("Account Ids in else part"+ accountIds);
					accountIds.add(accountDto.getId());
					deviceDto.setParentAccountId(accountIds);
				}
			}

			
			System.out.println("For update" + deviceDto.getId());
			System.out.println("before save");
			return deviceService.save(deviceDto);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Page<DeviceDto> getListofDevices(
			PagedRequest pageRequest, HttpServletRequest request) {
		try {
			return deviceService
					.findAllPage(
							pageRequest,
							request.getSession(false).getAttribute("accountId") != null ? request
									.getSession(false)
									.getAttribute("accountId").toString()
									: accountService.findByUserId(
											currentUserNameByPrincipal()
													.getId()).getId());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Problem occur get devices", e.getMessage());
			return null;
		}

	}

	@RequestMapping(value = "/carrier/list", method = RequestMethod.GET)
	public @ResponseBody List<CarrierDto> getListofCarriers() {
		try {
			return deviceService.getCarriers();
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Carriers list not get ", e.getMessage());
			return null;

		}
	}

	@RequestMapping(value = "/devicetype/list", method = RequestMethod.GET)
	public @ResponseBody List<DeviceTypeDto> getListofDeviceType() {
		try {
			return deviceService.getDeviceTypes();
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("DeviceType not get ", e.getMessage());
			return null;
		}

	}

	@RequestMapping(value = "/search/{searchterm}", method = RequestMethod.GET)
	public @ResponseBody List<DeviceDto> getDeviceBySearchTerm(
			@PathVariable String searchterm, HttpServletRequest request) {
		LOGGER.debug("Search term " + searchterm);
		try {
			return deviceService
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
