package com.auction.pro.device.dto;

import com.auction.pro.common.model.BaseModel;
import com.auction.pro.device.model.Carrier;

public class CarrierDto extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	public CarrierDto() {
		// TODO Auto-generated constructor stub
	}

	public CarrierDto(Carrier carrier) {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
