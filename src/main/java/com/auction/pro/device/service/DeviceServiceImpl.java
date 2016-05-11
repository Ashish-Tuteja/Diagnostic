package com.auction.pro.device.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.common.service.AbstractServiceImpl;
import com.auction.pro.device.dao.base.CarrierDao;
import com.auction.pro.device.dao.base.DeviceAccountMapDao;
import com.auction.pro.device.dao.base.DeviceDao;
import com.auction.pro.device.dto.CarrierDto;
import com.auction.pro.device.dto.DeviceDto;
import com.auction.pro.device.dto.DeviceTypeDto;
import com.auction.pro.device.model.Carrier;
import com.auction.pro.device.model.Device;
import com.auction.pro.device.model.DeviceType;
import com.auction.pro.device.service.base.DeviceService;

@Service
public class DeviceServiceImpl extends AbstractServiceImpl<DeviceDto, Device>
		implements DeviceService {
	@Autowired
	DeviceDao deviceDao;
	@Autowired
	DeviceAccountMapDao accountMapDao;
	@Autowired
	CarrierDao carrierDao;
	private boolean chekIfExists;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DeviceServiceImpl.class.getName());

	public DeviceDto save(DeviceDto entity) {
		// TODO Auto-generated method stub
		if (entity.getId() != null) {
			chekIfExists = deviceDao.exists((Device) getEntityFromDTO(entity,
					Device.class));
			return getDTOForEntity(deviceDao.save((Device) getEntityFromDTO(
					entity, Device.class)));
		} else {
			chekIfExists = deviceDao.exists((Device) getEntityFromDTO(entity,
					Device.class));
			return !chekIfExists ? getDTOForEntity(deviceDao
					.save((Device) getEntityFromDTO(entity, Device.class)))
					: null;
		}
	}

	public void delete(DeviceDto entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public AbstractDAO<Device> getDAO() {
		// TODO Auto-generated method stub
		return deviceDao;
	}

	@Override
	public DeviceDto getDTOForEntity(Device entity) {
		// TODO Auto-generated method stub
		return new DeviceDto(entity);
	}

	@Override
	public Object getEntityFromDTO(DeviceDto entity,
			@SuppressWarnings("rawtypes") Class classType) {
		if (classType.getSimpleName().equals("Device")) {
			return new Device(entity);
		}
		return null;

	}

	@Override
	public List<DeviceDto> getDTOsForEntities(Iterable<Device> entities) {
		// TODO Auto-generated method stub
		Iterator<Device> iteratedevice = entities.iterator();
		List<DeviceDto> deviceDTOs = new ArrayList<DeviceDto>();
		while (iteratedevice.hasNext()) {
			deviceDTOs.add(getDTOForEntity(iteratedevice.next()));
		}
		return deviceDTOs;
	}

	public List<DeviceDto> findBySerachterm(String searchterm,
			Serializable accountId) throws Exception {
		// TODO Auto-generated method stub
		return getDTOsForEntities(deviceDao.findBySerachterm(searchterm,
				accountId));
	}

	public List<CarrierDto> getCarriers() throws Exception {
		// TODO Auto-generated method stub
		return getCarrierDTOsForEntities(deviceDao.getCarriers());
	}

	public List<DeviceTypeDto> getDeviceTypes() throws Exception {
		// TODO Auto-generated method stub
		return getDeviceTypeDTOsForEntities(deviceDao.getDeviceTypes());
	}

	public List<CarrierDto> getCarrierDTOsForEntities(Iterable<Carrier> entities) {
		// TODO Auto-generated method stub
		Iterator<Carrier> iterator = entities.iterator();
		List<CarrierDto> carrier = new ArrayList<CarrierDto>();
		Carrier carrierEntity = null;
		CarrierDto carrierDto = null;
		while (iterator.hasNext()) {
			carrierDto = new CarrierDto();
			carrierEntity = (Carrier) iterator.next();
			carrierDto.setName(carrierEntity.getName());
			carrierDto.setId(carrierEntity.getId());
			carrier.add(carrierDto);
		}
		return carrier;
	}

	public List<DeviceTypeDto> getDeviceTypeDTOsForEntities(
			Iterable<DeviceType> entities) {
		// TODO Auto-generated method stub
		Iterator<DeviceType> iterator = entities.iterator();
		List<DeviceTypeDto> deviceTypeDTO = new ArrayList<DeviceTypeDto>();
		DeviceType deviceTypeEntity = null;
		DeviceTypeDto deviceTypeDto = null;
		while (iterator.hasNext()) {
			deviceTypeDto = new DeviceTypeDto();
			deviceTypeEntity = (DeviceType) iterator.next();
			deviceTypeDto.setName(deviceTypeEntity.getName());
			deviceTypeDto.setId(deviceTypeEntity.getId());
			deviceTypeDTO.add(deviceTypeDto);
		}
		return deviceTypeDTO;
	}

	public List<DeviceDto> getDevices() throws Exception {
		// TODO Auto-generated method stub
		return getDTOsForEntities(deviceDao.getDevices());
	}

	public CarrierDto findCarrierById(Serializable carrieId) {
		// TODO Auto-generated method stub
		Carrier carrier = carrierDao.findById(carrieId);
		CarrierDto carrierDto = new CarrierDto();
		carrierDto.setId(carrier.getId());
		carrierDto.setName(carrier.getName());
		return carrierDto;
	}

	public Page<DeviceDto> findAllPage(Pageable pageable, String parentAccountId)
			throws Exception {
		// TODO Auto-generated method stub
		return convertEntityPageToDTOPage(
				deviceDao.findAllPage(pageable, parentAccountId), pageable);
	}

	public DeviceDto findByDeviceIp(String ip) {
		// TODO Auto-generated method stub
		return getDTOForEntity(deviceDao.findByDeviceIp(ip));
	}

	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
	
	}

}
