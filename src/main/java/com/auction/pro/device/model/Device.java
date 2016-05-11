package com.auction.pro.device.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.common.model.BaseModel;
import com.auction.pro.device.dto.DeviceDto;

@Document(collection = "device")
public class Device extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> parentAccountId;
	private String deviceName;
	private String description;
	private String password;
	private String sim;
	private String carrierId;
	private String deviceTypeId;
	private String ip;

	public Device() {
		// TODO Auto-generated constructor stub
	}

	public Device(DeviceDto deviceDto) {
		// TODO Auto-generated constructor stub
		super(deviceDto);
		// this.parentAccountId = deviceDto.getParentAccountId();
		this.deviceName = !StringUtils.isEmpty(deviceDto.getDeviceName()) ? deviceDto
				.getDeviceName() : "";
		this.description = !StringUtils.isEmpty(deviceDto.getDescription()) ? deviceDto
				.getDescription() : "";
		this.password = !StringUtils.isEmpty(deviceDto.getPassword()) ? deviceDto
				.getPassword() : "";
		this.sim = !StringUtils.isEmpty(deviceDto.getSim()) ? deviceDto
				.getSim() : "";
		/*
		 * this.carrierId = !StringUtils.isEmpty(deviceDto.getCarrierId()) ?
		 * deviceDto .getCarrierId() : "";
		 */
		/*
		 * this.deviceTypeId = !StringUtils.isEmpty(deviceDto.getDeviceTypeId())
		 * ? deviceDto .getDeviceTypeId() : "";
		 */
		this.ip = !StringUtils.isEmpty(deviceDto.getIp()) ? deviceDto.getIp()
				: "";
		this.setCreatedAt(deviceDto.getCreatedAt());
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

	public List<String> getParentAccountid() {
		return parentAccountId;
	}

	public void setParentAccountid(List<String> parentAccountId) {
		this.parentAccountId = parentAccountId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
