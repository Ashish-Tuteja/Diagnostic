package com.auction.pro.vehicle.service.base;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auction.pro.common.service.AbstractService;
import com.auction.pro.vehicle.dto.VehicleDto;
import com.auction.pro.ecuController.model.EcuController;
import com.auction.pro.vehicle.model.GlobalParameters;

public interface VehicleService extends AbstractService<VehicleDto> {
	
	public List<VehicleDto> findBySerachterm(String searchterm,
			Serializable accountId) throws Exception;

	public Page<VehicleDto> findAllPage(Pageable pageable,
			String parentAccountId) throws Exception;

	void saveReport(VehicleDto reportDto) throws Exception;
	

	Map<String, Object> getReportsByGroupId(String groupid) throws Exception;

	public int updateTimeStamp(String vin) throws Exception;

	void insertVehicleReportGroupId(String groupId, String vin)
			throws Exception;


	public int uploadGlobalParameters(String[] ids, File tempfile, byte[] bytestream,
			String uploadoption);

	

	List<GlobalParameters> getDataList(String controllerId);
	
	VehicleDto findByVIN(String vin) throws Exception;
	

	EcuController getvehicleECU(String make , String model , String year);

	public void setControllerEcu(EcuController controllerParameters) throws Exception;

}