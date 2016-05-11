package com.auction.pro.vehicle.service.base;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auction.pro.common.service.AbstractService;
import com.auction.pro.vehicle.dto.VehicleDto;
import com.auction.pro.vehicle.filter.VehicleFilter;
import com.auction.pro.vehicle.model.Ecu;
import com.auction.pro.vehicle.model.GlobalParameter;

public interface VehicleService extends AbstractService<VehicleDto> {
	public List<VehicleDto> findBySerachterm(String searchterm,
			Serializable accountId) throws Exception;

	public Page<VehicleDto> findAllPage(Pageable pageable,
			String parentAccountId) throws Exception;

	void saveReport(VehicleDto reportDto) throws Exception;

	VehicleDto findByVIN(String vin) throws Exception;

	Map<String, Object> getReportsByGroupId(String groupid) throws Exception;

	public int updateTimeStamp(String vin) throws Exception;

	void insertVehicleReportGroupId(String groupId, String vin)
			throws Exception;

	public int uploadGlobalData(File tempfile, byte[] bytestream,
			String uploadoption);

	public int uploadVehicleEcu(File tempfile, byte[] bytestream,
			String uploadoption);

	public int uploadGlobalEcu(File tempfile, byte[] bytestream,
			String uploadoption);

	public List<String> getControllerIds(String make, String model,
			String startYear, String endYear);

	List<GlobalParameter> getDataList(String controllerId);

	Ecu getvehicleECU(VehicleFilter searchterm);
}
