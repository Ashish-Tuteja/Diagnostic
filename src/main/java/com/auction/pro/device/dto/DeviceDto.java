package com.auction.pro.device.dto;

import java.util.List;

import com.auction.pro.common.dto.BaseDTO;
import com.auction.pro.device.model.Device;

public class DeviceDto extends BaseDTO {

	private static final long serialVersionUID = 1L;
	private List<String> parentAccountId;
	private String deviceName;
	private String description;
	private String password;
	private String sim;
	private String carrierId;
	private String carrierName;
	private String deviceTypeId;
	private String deviceTypeName;
	private String ip;

	public DeviceDto() {
		// TODO Auto-generated constructor stub
	}

	public DeviceDto(Device entity) {
		// TODO Auto-generated constructor stub
		if (entity != null) {
			setId(entity.getId());
			this.parentAccountId = entity.getParentAccountid();
			this.deviceName = entity.getDeviceName();
			this.description = entity.getDescription();
			this.sim = entity.getSim();
			this.carrierId = entity.getCarrierId();
			this.deviceTypeId = entity.getDeviceTypeId();
			this.ip = entity.getIp();
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}

	public String getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public List<String> getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(List<String> parentAccountId) {
		this.parentAccountId = parentAccountId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
