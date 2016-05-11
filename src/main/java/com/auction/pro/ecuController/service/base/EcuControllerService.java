package com.auction.pro.ecuController.service.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auction.pro.common.service.AbstractService;
import com.auction.pro.ecuController.dto.EcuControllerDto;

public interface EcuControllerService extends AbstractService<EcuControllerDto> {
	List<EcuControllerDto> findBySerachterm(String searchterm, Serializable accountId)
			throws Exception;

//	List<CarrierDto> getCarriers() throws Exception;

//	List<DeviceTypeDto> getDeviceTypes() throws Exception;

	List<EcuControllerDto> getEcuControllers() throws Exception;

//	public CarrierDto findCarrierById(Serializable carrieId) throws Exception;

	public Page<EcuControllerDto> findAllPage(Pageable pageable, String parentAccountId)
			throws Exception;

	void delete(String id);
	

//	EcuControllerDto findByDeviceIp(String ip);
}
