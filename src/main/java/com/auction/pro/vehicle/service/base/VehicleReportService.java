package com.auction.pro.vehicle.service.base;

import java.util.List;

import com.auction.pro.common.service.AbstractService;
import com.auction.pro.vehicle.dto.VehicleReportDto;

public interface VehicleReportService extends AbstractService<VehicleReportDto> {
	List<VehicleReportDto> findReportsById(String vin) throws Exception;

	List<VehicleReportDto> findReportsByIp(String ip) throws Exception;
}
