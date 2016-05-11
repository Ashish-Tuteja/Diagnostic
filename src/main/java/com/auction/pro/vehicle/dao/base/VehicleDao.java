package com.auction.pro.vehicle.dao.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.vehicle.filter.VehicleFilter;
import com.auction.pro.vehicle.model.Ecu;
import com.auction.pro.vehicle.model.EcuController;
import com.auction.pro.vehicle.model.GlobalParameter;
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

	boolean setVehicleECU(List<Ecu> ecus) throws Exception;

	boolean setGlobalECU(List<EcuController> ecus) throws Exception;

	List<GlobalParameter> getDataList(String controllerId);

	List<String> getControllerIds(String make, String model, String startYear,
			String endYear);

	List<EcuController> getECUController(List<String> controllerIds);

	Ecu getvehicleECU(VehicleFilter searchterm);
}
