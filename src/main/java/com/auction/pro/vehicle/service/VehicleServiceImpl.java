package com.auction.pro.vehicle.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
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
import com.auction.pro.ecuController.model.EcuController;
import com.auction.pro.vehicle.dao.base.DeviceVehicleMapDao;
import com.auction.pro.vehicle.dao.base.VehicleDao;
import com.auction.pro.vehicle.dao.base.VehicleReportDao;
import com.auction.pro.vehicle.dto.VehicleDto;
import com.auction.pro.vehicle.model.DeviceVehicleMap;
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


	// Global Parameters Implementation
		public int uploadGlobalParameters(String[] controllerIds,File tempfile, byte[] bytestream,
				String uploadoption) {
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
				try{
				if(controllerIds.length != 0){
					LOGGER.info("Importing parameter file for selected controllers ");
					ecugbList = vehicleDao
							.getECUController(controllerIds);
					System.out.println("Size returned for existing controllerIds ::"+ecugbList.size());
					
					List<String> updateList = new ArrayList<String>();
					List<String> addList = new ArrayList<String>();
					
					
					if(!ecugbList.isEmpty()){
					for(GlobalParameters parameter :ecugbList){
						for(String id : controllerIds){
							if(parameter.getControllerId().equals(id)){
								
								if(!updateList.contains(id)){
									updateList.add(id);
									LOGGER.info("Ids in update list :" + updateList);
								}
								
							}else {
								if(!addList.contains(id)){
									addList.add(id);
									LOGGER.info("Ids in add list :" + addList);
									
								}
							}
							
						}
					}
					}
					
					if(!updateList.isEmpty()){
						LOGGER.info("Deleting parameters for selected controllers");
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
								GlobalParameters obj = new GlobalParameters(parameters);
								obj.setControllerId(controllerId);
								finalgbList.add(obj);	
							}
						}
						
						vehicleDao.setGlobalParameters(finalgbList);
						
						LOGGER.info("Each document in Parameter_tests updated with controller_ids for each selected Controller");
						
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


	
	public EcuController getvehicleECU(String make , String model , String year) {
		// TODO Auto-generated method stub
		return vehicleDao.getvehicleECU(make , model , year);
	}

	public void setControllerEcu(EcuController controllerParameters)
			throws Exception {
		// TODO Auto-generated method stub

		vehicleDao.setControllerEcuParameters(controllerParameters);

	}


}