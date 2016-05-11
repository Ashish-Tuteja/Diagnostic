package com.auction.pro.device.service.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auction.pro.common.service.AbstractService;
import com.auction.pro.device.dto.CarrierDto;
import com.auction.pro.device.dto.DeviceDto;
import com.auction.pro.device.dto.DeviceTypeDto;

public interface DeviceService extends AbstractService<DeviceDto> {
	List<DeviceDto> findBySerachterm(String searchterm, Serializable accountId)
			throws Exception;

	List<CarrierDto> getCarriers() throws Exception;

	List<DeviceTypeDto> getDeviceTypes() throws Exception;

	List<DeviceDto> getDevices() throws Exception;

	public CarrierDto findCarrierById(Serializable carrieId) throws Exception;

	public Page<DeviceDto> findAllPage(Pageable pageable, String parentAccountId)
			throws Exception;

	DeviceDto findByDeviceIp(String ip);
}
