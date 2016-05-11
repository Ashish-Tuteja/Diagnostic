package com.auction.pro.device.dto;

import com.auction.pro.common.model.BaseModel;
import com.auction.pro.device.model.DeviceType;

public class DeviceTypeDto extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String code;

	public DeviceTypeDto() {
		// TODO Auto-generated constructor stub
	}

	public DeviceTypeDto(DeviceType deviceTypeDto) {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
