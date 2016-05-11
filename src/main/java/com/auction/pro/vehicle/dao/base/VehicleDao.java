package com.auction.pro.vehicle.dao.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.vehicle.filter.VehicleFilter;
import com.auction.pro.vehicle.model.Ecu;
import com.auction.pro.vehicle.model.EcuController_backup;
import com.auction.pro.vehicle.model.EcuControllers;
import com.auction.pro.vehicle.model.GlobalParameter;
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

	void setGlobalparameter(List<GlobalParameter> parameters) throws Exception;

	void setGlobalParameters(List<GlobalParameters> parameters)
			throws Exception;
	public void setControllerEcuParameters(EcuControllers controllerParameters) 
			throws Exception;
	
	void updateGlobalParameters(List<GlobalParameters> parameters)
			throws Exception;

	boolean setVehicleECU(List<Ecu> ecus) throws Exception;

	boolean setGlobalECU(List<EcuController_backup> ecus) throws Exception;

	List<GlobalParameters> getDataList(String controllerId);
	List<GlobalParameters> getDataListParameters( String vehicleControllerId);
	List<EcuControllers> getEcuListParameters();


	List<EcuController_backup> getECUController(List<String> controllerIds);
	Ecu getvehicleECU(VehicleFilter serachterm);

	EcuControllers getvehicleECU(String make , String model , String year);
	

	//Vehicle findDefaultVehicle() throws Exception;

	
}