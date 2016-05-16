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

import com.auction.pro.common.constants.NavResearchConstants;
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
import com.auction.pro.vehicle.model.EcuController_backup;
import com.auction.pro.vehicle.model.EcuControllers;
import com.auction.pro.vehicle.model.GlobalParameter;
import com.auction.pro.vehicle.model.GlobalParameters;
import com.auction.pro.vehicle.model.Vehicle;
import com.auction.pro.vehicle.model.VehicleReport;
import com.auction.pro.vehicle.service.base.VehicleService;

@Service(value = "vehicleService")
public class VehicleServiceImpl extends
		AbstractServiceImpl<VehicleDto, Vehicle> implements VehicleService,
		DatauploadService, NavResearchConstants {

	@Autowired
	VehicleDao vehicleDao;
	@Autowired
	DeviceVehicleMapDao deviceVehicleMapDao;
	@Autowired
	VehicleReportDao reportDao;

	public static String filename;
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
		System.out.println(vin);
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

	// Global Parameters Implementation
		@SuppressWarnings("unused")
		public int uploadGlobalParameters(String[] controllerIds,File tempfile, byte[] bytestream,
				String uploadoption) {
//			String vehicleControllerID = "";
			List<GlobalParameters> gbList = new ArrayList<GlobalParameters>();
			List<GlobalParameters> finalgbList = new ArrayList<GlobalParameters>();
			List<GlobalParameters> ecugbList = new ArrayList<GlobalParameters>();
			gbList.clear();
			ecugbList.clear();
			finalgbList.clear();
				Map<Integer, List<String>> map = uploadData(tempfile, bytestream,
						uploadoption);

				if (map == null) {
					LOGGER.error("Csv file not read properly");
					return 1;
				}
			
				/*int counter = 0;
				// Check Name of file imported
				filename = tempfile.getName();
				String filename = tempfile.getName();
				String[] filenames = filename.split("-{1}|\\_{1}|\\.{1}");
				while (counter < filenames.length) {
					counter++;
				}
				
				vehicleControllerID = filenames[0] + "-" + filenames[1] + "-"
						+ filenames[2] + "-" + filenames[3];
				System.out.println("Vehicles--Controllers--ID to be appended in Global Parameters List -->> "+ vehicleControllerID);
				gbList = vehicleDao
						.getDataListParameters(vehicleControllerID);*/
//			System.out.println("controllers================>>>"+(controllerIds[0]));
//			System.out.println("controllers size================>>>"+(controllerIds.length));
			
				try{
				if(controllerIds.length != 0){
					LOGGER.info("Importing parameter file for selected controllers ");
					ecugbList = vehicleDao
							.getECUController(controllerIds);
					System.out.println("Size returned "+ecugbList.size());
					
					List<String> updateList = new ArrayList<String>();
					List<String> addList = new ArrayList<String>();
					
					
					if(!ecugbList.isEmpty()){
					for(GlobalParameters parameter :ecugbList){
						System.out.println("parameter id "+parameter.getControllerId());
						for(String id : controllerIds){
							System.out.println("id in inner "+id);
							if(parameter.getControllerId().equals(id)){
								
								if(!updateList.contains(id)){
									System.out.println("in update list");
									updateList.add(id);
								}
								
							}else {
								if(!addList.contains(id)){
									addList.add(id);
								}
							}
							
						}
					}
					}
					System.out.println("update list "+updateList);
					System.out.println("add list "+addList);
					
					if(!updateList.isEmpty()){
						LOGGER.info("Deleting parameters for selected controllers");
////						gbList.clear(); //clearing before importing already imported file
////						ecugbList.clear();
////						finalgbList.clear();
//						
//						for (Entry<Integer, List<String>> data : map.entrySet()) {
//							gbList.add(GlobalParameters
//									.setGlobalparameters(data.getValue()));
//						}
//						GlobalParameters.clearSequence();//clearing sequence when importing new file
//						
//						
//						for(GlobalParameters parameters : gbList){
//							for(String controllerId : controllerIds){
//								LOGGER.info("controllerid :: " + controllerId+ "   for each controller");
//								GlobalParameters obj = new GlobalParameters(parameters);
//								obj.setControllerId(controllerId);
//								finalgbList.add(obj);	
//							}
//						}
						
						vehicleDao.deleteGlobalParameters(updateList);
					}
					
					
						LOGGER.info("Saving parameters for selected controllers");
						LOGGER.info("Importing new file");
						for (Entry<Integer, List<String>> data : map.entrySet()) {
							gbList.add(GlobalParameters
									.setGlobalparameters(data.getValue()));
						}
						GlobalParameters.clearSequence();//clearing sequence when importing new file

						for(GlobalParameters parameters : gbList){
							for(String controllerId : controllerIds){
								LOGGER.info("controllerid :: " + controllerId+ "   for each controller");
								GlobalParameters obj = new GlobalParameters(parameters);
								obj.setControllerId(controllerId);
								finalgbList.add(obj);	
							}
						}
						
						vehicleDao.setGlobalParameters(finalgbList);
						
						LOGGER.info("Each document in Parameter_tests updated with controller_ids for each selected Controller");
						
						
					
					/*else{
						LOGGER.info("Updating parameters for selected controllers");
						gbList.clear(); //clearing before importing already imported file
						ecugbList.clear();
						finalgbList.clear();
						for (Entry<Integer, List<String>> data : map.entrySet()) {
							gbList.add(GlobalParameters
									.setGlobalparameters(data.getValue()));
						}
						GlobalParameters.clearSequence();//clearing sequence when importing new file
						
						
						for(GlobalParameters parameters : gbList){
							for(String controllerId : controllerIds){
								LOGGER.info("controllerid :: " + controllerId+ "   for each controller");
								GlobalParameters obj = new GlobalParameters(parameters);
								obj.setControllerId(controllerId);
								finalgbList.add(obj);	
							}
						}
						
						vehicleDao.deleteGlobalParameters(finalgbList);
					}*/
					
				}else{
					LOGGER.info("No parameter_tests saved !! Please select controllers first");
				}
				
		
				tempfile.deleteOnExit();	
				return 0;
				
				}catch (Exception e) {
					e.printStackTrace();
					LOGGER.error("CSV file not parsed properly ", e.getMessage());
					return 1;
				}
		}
		
		// End tags for Global service Implementation for parameter Tests
		
				/*try{
				if(gbList.size() == 0){
					LOGGER.info("Importing new file");
					for (Entry<Integer, List<String>> data : map.entrySet()) {
						gbList.add(GlobalParameters
								.setGlobalparameters(data.getValue(),
										vehicleControllerID));
					}
					GlobalParameters.clearSequence();//clearing sequence when importing new file

					for(GlobalParameters parameters : gbList){
						for(String controllerId : controllerIds){
							LOGGER.info("controllerid :: " + controllerId+ "   for each controller");
							GlobalParameters obj = new GlobalParameters(parameters);
							obj.setControllerId(controllerId);
							finalList.add(obj);	
						}
					}
					
					vehicleDao.setGlobalParameters(finalList);
					
					LOGGER.info("Each document in Parameter_tests updated with auto_generated_controller_ids for each Controller");
				}else{
					
					LOGGER.error("FileName already exists !! Updating imported file");
					gbList.clear(); //clearing the already uploaded parameters
					for (Entry<Integer, List<String>> data : map.entrySet()) {
						gbList.add(GlobalParameters
								.setGlobalparameters(data.getValue(),
										vehicleControllerID));
					}
					GlobalParameters.clearSequence();
//					if (ecus != null) {
//						for (EcuControllers ecu : ecus) {
//							if (ecu.getMake()
//									.equalsIgnoreCase(filenames[0])
//									&& ecu.getModel().equalsIgnoreCase(
//											filenames[1])
//									&& ecu.getYear().equalsIgnoreCase(
//											filenames[2])) {
//								for (GlobalParameters gb : gbList) {
//									gb.setControllerId(ecu
//											.getControllerId());
//								}
//							}
//						}
//
//					} else {
//						LOGGER.error("No ecus exists!!");
//					}
					vehicleDao.updateGlobalParameters(gbList);
					LOGGER.info("FileName updated with vehicleControllerID appended in parameter_tests");
					
				}
				
				tempfile.deleteOnExit();	
			return 0;
			
			}catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("CSV file not parsed properly ", e.getMessage());
				return 1;
			}
		}*/

		// End tags for Global service Implementation

	public int uploadVehicleEcu(File tempfile, byte[] bytestream,
			String uploadoption) {
		System.out.println("upload vehicle ecu service ");
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
				System.out.println(vehicleECUList);
				List<String> controllerIds = vehicleECUList.subList(
						VEHICLE_ECU_CONTROLLERS_FROM, vehicleECUList.size());
				System.out.println(controllerIds);
				if (controllerIds == null || controllerIds.size() <= 0) {
					continue;
				}
				controllerIds.removeAll(Arrays.asList("", null));
				List<EcuControllers> ecuControllers = vehicleDao
						.getECUControllers(controllerIds);
				System.out.println(ecuControllers);
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
		System.out.println("upload global ecu service ");
		List<EcuController_backup> ecuController = new ArrayList<EcuController_backup>();
		try {
			Map<Integer, List<String>> map = uploadData(tempfile, bytestream,
					uploadoption);
			System.out.println(map);
			if (map == null) {
				LOGGER.error("Excel not read properly");
				return 1;
			}
			for (Entry<Integer, List<String>> data : map.entrySet()) {
				ecuController.add(EcuController_backup.setGlobalECU(data.getValue()));
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
			return (E) CommonUtils.readCsv(tempfile, uploadoption);

		} catch (Exception e) {
			LOGGER.error("Csv file not parsed properly ", e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public List<GlobalParameters> getDataList(String controllerIds) {
		return vehicleDao.getDataList(controllerIds);
	}

	public List<GlobalParameters> getDataListParameters(String vehicleControllerId) {
		return vehicleDao.getDataListParameters(vehicleControllerId);
	}


	public Ecu getvehicleECU(VehicleFilter searchterm) {
		// TODO Auto-generated method stub
		return vehicleDao.getvehicleECU(searchterm);
	}
	
	public EcuControllers getvehicleECU(String make , String model , String year) {
		// TODO Auto-generated method stub
		return vehicleDao.getvehicleECU(make , model , year);
	}

	public void setControllerEcu(EcuControllers controllerParameters)
			throws Exception {
		// TODO Auto-generated method stub

		vehicleDao.setControllerEcuParameters(controllerParameters);

	}

	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
	}

	

	public List<EcuControllers> getEcuListParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}