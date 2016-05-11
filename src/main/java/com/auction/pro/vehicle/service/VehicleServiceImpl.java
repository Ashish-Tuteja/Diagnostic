package com.auction.pro.vehicle.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.auction.pro.common.constants.CimbleConstants;
import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.common.service.AbstractServiceImpl;
import com.auction.pro.common.service.base.DatauploadService;
import com.auction.pro.common.utils.CommonUtils;
import com.auction.pro.vehicle.dao.base.DeviceVehicleMapDao;
import com.auction.pro.vehicle.dao.base.VehicleDao;
import com.auction.pro.vehicle.dao.base.VehicleReportDao;
import com.auction.pro.vehicle.dto.VehicleDto;
import com.auction.pro.vehicle.filter.VehicleFilter;
import com.auction.pro.vehicle.model.DeviceVehicleMap;
import com.auction.pro.vehicle.model.Ecu;
import com.auction.pro.vehicle.model.EcuController;
import com.auction.pro.vehicle.model.GlobalParameter;
import com.auction.pro.vehicle.model.Vehicle;
import com.auction.pro.vehicle.model.VehicleReport;
import com.auction.pro.vehicle.service.base.VehicleService;

@Service(value = "vehicleService")
public class VehicleServiceImpl extends
		AbstractServiceImpl<VehicleDto, Vehicle> implements VehicleService,
		DatauploadService, CimbleConstants {

	@Autowired
	VehicleDao vehicleDao;
	@Autowired
	DeviceVehicleMapDao deviceVehicleMapDao;
	@Autowired
	VehicleReportDao reportDao;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(VehicleServiceImpl.class.getName());

	public VehicleDto save(VehicleDto entity) {
		// TODO Auto-generated method stub
		/*
		 * Vehicle existingVehicle = null; try { existingVehicle =
		 * vehicleDao.findByVIN(entity.getVin()); } catch (Exception e) { //
		 * TODO: handle exception e.printStackTrace(); }
		 */
		if (entity.getId() != null) {
			return getDTOForEntity(vehicleDao.save((Vehicle) getEntityFromDTO(
					entity, Vehicle.class)));
		} else {

			LOGGER.info("Save new vehicle");
			return saveEntity(entity);
		}

	}

	public void delete(VehicleDto entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public AbstractDAO<Vehicle> getDAO() {
		// TODO Auto-generated method stub
		return vehicleDao;
	}

	@Override
	public VehicleDto getDTOForEntity(Vehicle entity) {
		// TODO Auto-generated method stub
		return new VehicleDto(entity);
	}

	@Override
	public Object getEntityFromDTO(VehicleDto entity,
			@SuppressWarnings("rawtypes") Class classType) {
		if (classType.getSimpleName().equals("Vehicle")) {
			return new Vehicle(entity);
		} else if (classType.getSimpleName().equals("DeviceVehicleMap")) {
			return new DeviceVehicleMap(entity);
		} else if (classType.getSimpleName().equals("VehicleReport")) {
			LOGGER.info("In VehicleReport");
			return new VehicleReport(entity);
		}
		return null;

	}

	@Override
	public List<VehicleDto> getDTOsForEntities(Iterable<Vehicle> entities) {
		// TODO Auto-generated method stub
		Iterator<Vehicle> iterator = entities.iterator();
		List<VehicleDto> vehicleDTOs = new ArrayList<VehicleDto>();
		while (iterator.hasNext()) {
			vehicleDTOs.add(getDTOForEntity(iterator.next()));
		}
		return vehicleDTOs;
	}

	public List<VehicleDto> findBySerachterm(String searchterm,
			Serializable accountId) throws Exception {
		// TODO Auto-generated method stub
		return getDTOsForEntities(vehicleDao.findBySerachterm(searchterm,
				accountId));
	}

	public VehicleDto saveEntity(VehicleDto entity) {
		Vehicle dto = vehicleDao.save((Vehicle) getEntityFromDTO(entity,
				Vehicle.class));
		/*
		 * entity.setId(dto.getId());
		 * deviceVehicleMapDao.save((DeviceVehicleMap) getEntityFromDTO(entity,
		 * DeviceVehicleMap.class));
		 */
		return getDTOForEntity(dto);
	}

	public Page<VehicleDto> findAllPage(Pageable pageable,
			String parentAccountId) throws Exception {
		// TODO Auto-generated method stub
		return convertEntityPageToDTOPage(
				vehicleDao.findAllPage(pageable, parentAccountId), pageable);
	}

	public void saveReport(VehicleDto reportDto) {
		// TODO Auto-generated method stub
		reportDao.save((VehicleReport) getEntityFromDTO(reportDto,
				VehicleReport.class));
	}

	public VehicleDto findByVIN(String vin) throws Exception {
		// TODO Auto-generated method stub
		return vehicleDao.findByVIN(vin) != null ? getDTOForEntity(vehicleDao
				.findByVIN(vin)) : null;

	}

	public Map<String, Object> getReportsByGroupId(String groupid)
			throws Exception {
		// TODO Auto-generated method stub
		return reportDao.findbyGroupId(groupid);
	}

	public int updateTimeStamp(String vin) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("Update timestamp");
		return vehicleDao.updateTimeStamp(vin);
	}

	public void insertVehicleReportGroupId(String groupId, String vin)
			throws Exception {
		// TODO Auto-generated method stub
		vehicleDao.insertVehicleReportGroupId(groupId, vin);
	}

	public int uploadGlobalData(File tempfile, byte[] bytestream,
			String uploadoption) {
		// TODO Auto-generated method stub
		System.out.println("inside global upload");
		List<GlobalParameter> globalParameters = new ArrayList<GlobalParameter>();
		try {
			Map<Integer, List<String>> map = uploadData(tempfile, bytestream,
					uploadoption);
			if (map == null) {
				System.out.println("Excel not read properly");
				LOGGER.error("Excel not read properly");
				return 1;
			}
			for (Entry<Integer, List<String>> data : map.entrySet()) {
				globalParameters.add(GlobalParameter.setGlobalparameter(data
						.getValue()));
			}
			vehicleDao.setGlobalparameter(globalParameters);
			tempfile.deleteOnExit();
			return 0;
		} catch (Exception e) {
			System.out.println("in Service");
			e.printStackTrace();
			LOGGER.error("Excel file not parse properly ", e.getMessage());
			return 1;
		}

	}

	public int uploadVehicleEcu(File tempfile, byte[] bytestream,
			String uploadoption) {
		System.out.println("uploadGlobal data service ");
		List<Ecu> vehicleECUs = new ArrayList<Ecu>();
		try {
			Map<Integer, List<String>> map = uploadData(tempfile, bytestream,
					uploadoption);

			if (map == null) {
				System.out.println("Excel not parse properly ");
				LOGGER.error("Excel not read properly");
				return 1;
			}
			for (Entry<Integer, List<String>> ecu : map.entrySet()) {
				System.out.println(ecu.getValue());
			}
			for (Entry<Integer, List<String>> data : map.entrySet()) {
				List<String> vehicleECUList = data.getValue();
				List<String> controllerIds = vehicleECUList.subList(
						VEHICLE_ECU_CONTROLLERS_FROM, vehicleECUList.size());
				if (controllerIds == null || controllerIds.size() <= 0) {
					continue;
				}
				controllerIds.removeAll(Arrays.asList("", null));
				List<EcuController> ecuControllers = vehicleDao
						.getECUController(controllerIds);
				ecuControllers.removeAll(Arrays.asList("", null));
				if (ecuControllers != null && ecuControllers.size() > 0) {
					vehicleECUs.add(Ecu.setVehicleECU(data.getValue(),
							ecuControllers));
				}
			}
			boolean isUpload = vehicleDao.setVehicleECU(vehicleECUs);
			if (!isUpload) {
				tempfile.deleteOnExit();
				return 1;
			}
			tempfile.deleteOnExit();
			return 0;
		} catch (Exception e) {
			LOGGER.error("Excel file not parse properly ", e.getMessage());
			e.printStackTrace();
			return 1;
		}

	}

	public int uploadGlobalEcu(File tempfile, byte[] bytestream,
			String uploadoption) {
		List<EcuController> ecuController = new ArrayList<EcuController>();
		try {
			Map<Integer, List<String>> map = uploadData(tempfile, bytestream,
					uploadoption);
			if (map == null) {
				LOGGER.error("Excel not read properly");
				return 1;
			}
			for (Entry<Integer, List<String>> data : map.entrySet()) {
				ecuController.add(EcuController.setGlobalECU(data.getValue()));
			}
			boolean isUpload = vehicleDao.setGlobalECU(ecuController);
			if (!isUpload) {
				tempfile.deleteOnExit();
				return 1;
			}
			tempfile.deleteOnExit();
			return 0;
		} catch (Exception e) {
			LOGGER.error("Excel file not parse properly ", e.getMessage());
			e.printStackTrace();
			return 1;
		}
	}

	@SuppressWarnings("unchecked")
	public <E> E uploadData(File tempfile, byte[] bytestream,
			String uploadoption) {
		// TODO Auto-generated method stub
		try {
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(tempfile));
			stream.write(bytestream);
			stream.close();
			return (E) CommonUtils.readExcel(tempfile, uploadoption);

		} catch (Exception e) {
			LOGGER.error("Excel file not parse properly ", e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public List<GlobalParameter> getDataList(String controllerIds) {
		return vehicleDao.getDataList(controllerIds);
	}

	public List<String> getControllerIds(String make, String model,
			String startYear, String endYear) {
		return vehicleDao.getControllerIds(make, model, startYear, endYear);
	}

	public Ecu getvehicleECU(VehicleFilter searchterm) {
		// TODO Auto-generated method stub
		return vehicleDao.getvehicleECU(searchterm);
	}
	/*public static void main(String[] args) {
	File file = new File("/home/infoobjects/Videos/parameter_desc.xlsx");
	String uploadoption = "";
	Map<Integer, List<String>> map = null;

	try {
		// To connect to mongodb server
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		// Now connect to your databases
		DB db = mongoClient.getDB("auctionpro");

		DBCollection coll = db.createCollection("parameter_description",
				null);
		System.out.println("Collection created successfully");
		try {
			map = CommonUtils.readExcel(file, uploadoption);
			for (Entry<Integer, List<String>> string : map.entrySet()) {
				List<String> values = string.getValue();
				BasicDBObject doc = new BasicDBObject("parameterId",
						Integer.parseInt(values.get(0))).append(
						"description", values.get(1)).append("units",
						values.get(2));
				coll.insert(doc);
				System.out.println("Document inserted successfully");

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	} catch (Exception e) {
		System.err.println(e.getClass().getName() + ": " + e.getMessage());
	}

}*/
}
