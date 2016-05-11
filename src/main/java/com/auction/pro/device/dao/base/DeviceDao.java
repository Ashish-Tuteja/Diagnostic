package com.auction.pro.device.dao.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.device.model.Carrier;
import com.auction.pro.device.model.Device;
import com.auction.pro.device.model.DeviceType;

public interface DeviceDao extends AbstractDAO<Device> {
	List<Device> findBySerachterm(String searchterm, Serializable accountId)
			throws Exception;

	List<Carrier> getCarriers() throws Exception;

	List<DeviceType> getDeviceTypes() throws Exception;

	public List<Device> getDevices() throws Exception;

	public Page<Device> findAllPage(Pageable pageable, String parentAccountId)
			throws Exception;

	Device findByDeviceIp(String ip);
}
