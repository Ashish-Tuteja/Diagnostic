package com.auction.pro.vehicle.dao.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.ecuController.model.EcuController;
import com.auction.pro.vehicle.model.GlobalParameters;
import com.auction.pro.vehicle.model.Vehicle;

public interface VehicleDao extends AbstractDAO<Vehicle> {
	public List<Vehicle> findBySerachterm(String searchterm,
			Serializable accountId) throws Exception;

	public Page<Vehicle> findAllPage(Pageable pageable, String parentAccountId)
			throws Exception;

	public Vehicle findByVIN(String vin) throws Exception;

	public int updateTimeStamp(String vin) throws Exception;

	public void setVehicleReportCount(String vin) throws Exception;

	void insertVehicleReportGroupId(String groupId, String vin)
			throws Exception;


	void setGlobalParameters(List<GlobalParameters> parameters)
			throws Exception;
	public void setControllerEcuParameters(EcuController controllerParameters) 
			throws Exception;
	
	void deleteGlobalParameters(List<String> list)
			throws Exception;



	List<GlobalParameters> getDataList(String controllerId);

	List<GlobalParameters> getECUController(String[] controllerIds);
	
	List<EcuController> getECUControllers(List<String> controllerIds);
	
	EcuController getvehicleECU(String make , String model , String year);
	

	
}