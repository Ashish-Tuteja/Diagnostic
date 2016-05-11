package com.auction.pro.device.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.common.model.BaseModel;
import com.auction.pro.device.dto.DeviceDto;

@Document(collection = "deviceaccountmap")
public class DeviceAccountMap extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accountId;
	private String deviceId;

	public DeviceAccountMap() {
		// TODO Auto-generated constructor stub
	}

	public DeviceAccountMap(DeviceDto dto) {
		// TODO Auto-generated constructor stub
		this.setDeviceId(dto.getId());

	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}
