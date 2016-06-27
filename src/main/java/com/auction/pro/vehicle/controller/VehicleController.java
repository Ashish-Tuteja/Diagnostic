package com.auction.pro.vehicle.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.auction.pro.account.dto.AccountDto;
import com.auction.pro.account.service.base.AccountService;
import com.auction.pro.common.controller.AbstractController;
import com.auction.pro.common.service.base.DatauploadService;
import com.auction.pro.common.utils.PagedRequest;
import com.auction.pro.device.dto.DeviceDto;
import com.auction.pro.device.service.base.DeviceService;
import com.auction.pro.queue.Producer;
import com.auction.pro.vehicle.dto.VehicleDto;
import com.auction.pro.vehicle.dto.VehicleReportDto;
import com.auction.pro.vehicle.service.base.VehicleReportService;
import com.auction.pro.vehicle.service.base.VehicleService;

@Controller
@RequestMapping("/vehicle")
public class VehicleController extends AbstractController {

	@Autowired
	VehicleService vehicleService;
	@Autowired
	VehicleReportService reportService;
	@Autowired
	DeviceService deviceService;
	@Autowired
	AccountService accountService;
	@Autowired
	Producer vehicleProducer;
	@Autowired
	@Qualifier(value = "vehicleService")
	DatauploadService datauploadService;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(VehicleController.class.getName());

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	protected @ResponseBody VehicleDto save(@RequestBody VehicleDto vehicleDto,
			HttpServletRequest request) {
		try {
			if (vehicleDto.getId() == null) {
				if (request.getSession(false).getAttribute("accountId") != null) {
					List<String> accountIDs = accountService.findById(
							request.getSession(false).getAttribute("accountId")
									.toString()).getParentAccountIds();
					accountIDs.add(request.getSession(false)
							.getAttribute("accountId").toString());
					vehicleDto.setParentAccountId(accountIDs);
				} else {
					AccountDto accountDto = accountService
							.findByUserId(currentUserNameByPrincipal().getId());
					List<String> parentAccountIds = accountDto
							.getParentAccountIds();
					parentAccountIds.add(accountDto.getId());
					vehicleDto.setParentAccountId(parentAccountIds);
				}
			}
			return vehicleService.save(vehicleDto);
		} catch (Exception e) {
			LOGGER.error("", e.getMessage());
			return null;
		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Page<VehicleDto> getListofVehicles(
			PagedRequest pageable, HttpServletRequest request) {
		try {
			return vehicleService
					.findAllPage(
							pageable,
							request.getSession(false).getAttribute("accountId") != null ? request
									.getSession(false)
									.getAttribute("accountId").toString()
									: accountService.findByUserId(
											currentUserNameByPrincipal()
													.getId()).getId());

		} catch (Exception e) {
			LOGGER.error("", e.getMessage());
			return null;
		}

	}

	@RequestMapping(value = "/device/list", method = RequestMethod.GET)
	public @ResponseBody List<DeviceDto> getListofDevices() {
		try {
			return deviceService.getDevices();
		} catch (Exception e) {
			LOGGER.error("", e.getMessage());
			return null;
		}

	}

	@RequestMapping(value = "/search/{searchterm}", method = RequestMethod.GET)
	public @ResponseBody List<VehicleDto> getVehicleBySearchTerm(
			@PathVariable String searchterm, HttpServletRequest request) {
		try {
			return vehicleService
					.findBySerachterm(
							searchterm,
							request.getSession(false).getAttribute("accountId") != null ? request
									.getSession(false)
									.getAttribute("accountId").toString()
									: accountService.findByUserId(
											currentUserNameByPrincipal()
													.getId()).getId());
		} catch (Exception e) {
			LOGGER.error("", e.getMessage());
			return null;
		}

	}

	@RequestMapping(value = "/savevehicle", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody int saveVehicle(@RequestBody VehicleDto dto) {
		try {
			return vehicleProducer.sendMessage(dto.getVin() + ","
					+ dto.getServerIP());
		} catch (Exception e) {
			LOGGER.error("Vehicle not save ", e.getMessage());
			return 0;
		}

	}

	@RequestMapping(value = "/setreport", method = RequestMethod.POST, produces = "application/json")
	private @ResponseBody int saveReport(@RequestBody VehicleDto report) {
		try {
			return vehicleProducer.sendMessage(report);
		} catch (Exception e) {
			LOGGER.error("Report not save ", e.getMessage());
			return 0;
		}
	}

	@RequestMapping(value = "/findreportsbyvin/{vin}", method = RequestMethod.GET, produces = "application/json")
	private @ResponseBody List<VehicleReportDto> getReportsInfobyVIN(
			@PathVariable String vin) {
		try {
			System.out.println("size of reports" +reportService.findReportsById(vin).size());
			return reportService.findReportsById(vin);
		} catch (Exception e) {
			LOGGER.error("", e.getMessage());
			return null;
		}

	}

	@RequestMapping(value = "/findreportsbyip/{ip:.+}", method = RequestMethod.GET, produces = "application/json")
	private @ResponseBody List<VehicleReportDto> getReportsInfobyIP(
			@PathVariable String ip) {
		try {
			return reportService.findReportsByIp(ip);
		} catch (Exception e) {
			LOGGER.error("", e.getMessage());
			return null;
		}

	}

	@RequestMapping(value = "/findreportbygroupid/{groupid}", method = RequestMethod.GET, produces = "application/json")
	private @ResponseBody Map<String, Object> getVechicleByGroupId(
			@PathVariable String groupid) {
		try {

			return vehicleService.getReportsByGroupId(groupid);
		} catch (Exception e) {
			LOGGER.error("", e.getMessage());
			return null;
		}

	}

	
	//Adding global parameters 1 file combi for 3 files
	@RequestMapping(value = "/upload/globalparameters", method = RequestMethod.POST)
	private @ResponseBody int uploadGlobalParameters(
			@RequestParam(value = "data") String uploadoption,
			@RequestParam(value = "file", required = true) MultipartFile file,
			HttpServletRequest request) {
//			@RequestBody String[] controllerIds) {
		// TODO Auto-generated method stub
		
		try {
			String[] controllerIds = request.getHeader("ids").split(",");
			String fileName = file.getOriginalFilename();
			if (!file.isEmpty()) {
				try {
					
					byte[] bytes = file.getBytes();
					// Creating the directory to store file
					String rootPath = System.getProperty("user.home");
					File tempfile = new File(rootPath + File.separator
							+ fileName);
					tempfile.deleteOnExit();
					// Files.deleteIfExists(tempfile.toPath());
					return vehicleService.uploadGlobalParameters(controllerIds,tempfile, bytes,
							uploadoption);
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.info("You failed to upload " + fileName + " => "
							+ e.getMessage());
					return 1;
				}
			} else {
				System.out.println("You failed to upload ");
				LOGGER.info("You failed to upload " + fileName
						+ " because the file was empty.");
				return 1;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("in controller");
			e.printStackTrace();
			LOGGER.error("Global Parameter error " + e.getMessage());
			return 1;
		}

	}
	//End for global parameters
	
	
	@RequestMapping(value = "/upload/globaldata", method = RequestMethod.POST)
	private @ResponseBody int globalDataUpload(
			@RequestParam(value = "data") String uploadoption,
			@RequestParam(value = "file", required = true) MultipartFile file) {
		// TODO Auto-generated method stub
		System.out.println("File name " + file.getOriginalFilename());
		try {
			String fileName = file.getOriginalFilename();
			if (!file.isEmpty()) {
				try {
					
					byte[] bytes = file.getBytes();
					// Creating the directory to store file
					String rootPath = System.getProperty("user.home");
					File tempfile = new File(rootPath + File.separator
							+ fileName);
					tempfile.deleteOnExit();
					// Files.deleteIfExists(tempfile.toPath());
					System.out.println("uploadGlobal data ");
					return vehicleService.uploadGlobalData(tempfile, bytes,
							uploadoption);
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.info("You failed to upload " + fileName + " => "
							+ e.getMessage());
					return 1;
				}
			} else {
				System.out.println("You failed to upload ");
				LOGGER.info("You failed to upload " + fileName
						+ " because the file was empty.");
				return 1;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("in controller");
			e.printStackTrace();
			LOGGER.error("Global Parameter error " + e.getMessage());
			return 1;
		}

	}

	@RequestMapping(value = "/upload/vehicleecu", method = RequestMethod.POST)
	private @ResponseBody int vehicleecuDataUpload(
			@RequestParam(value = "data") String uploadoption,
			@RequestParam(value = "file", required = true) MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			String fileName = file.getOriginalFilename();
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					// Creating the directory to store file
					String rootPath = System.getProperty("user.home");
					File tempfile = new File(rootPath + File.separator
							+ fileName);
					tempfile.deleteOnExit();
					// Files.deleteIfExists(tempfile.toPath());
					return vehicleService.uploadVehicleEcu(tempfile, bytes,
							uploadoption);
				} catch (Exception e) {
					LOGGER.info("You failed to upload " + fileName + " => "
							+ e.getMessage());
					return 1;
				}
			} else {
				LOGGER.info("You failed to upload " + fileName
						+ " because the file was empty.");
				return 1;
			}

		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Global Parameter error " + e.getMessage());
			return 1;
		}

	}

	@RequestMapping(value = "/upload/globalecu", method = RequestMethod.POST)
	private @ResponseBody int globalecuDataUpload(
			@RequestParam(value = "data") String uploadoption,
			@RequestParam(value = "file", required = true) MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			String fileName = file.getOriginalFilename();
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					// Creating the directory to store file
					String rootPath = System.getProperty("user.home");
					File tempfile = new File(rootPath + File.separator
							+ fileName);
					tempfile.deleteOnExit();
					// Files.deleteIfExists(tempfile.toPath());
					return vehicleService.uploadGlobalEcu(tempfile, bytes,
							uploadoption);
				} catch (Exception e) {
					LOGGER.info("You failed to upload " + fileName + " => "
							+ e.getMessage());
					return 1;
				}
			} else {
				LOGGER.info("You failed to upload " + fileName
						+ " because the file was empty.");
				return 1;
			}

		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Global Parameter error " + e.getMessage());
			return 1;
		}

	}

}
