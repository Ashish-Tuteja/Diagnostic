package com.auction.pro.vehicle.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.common.model.BaseModel;

@Document(collection = "manufacturer")
public class Manufacturer extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	
	private String manufacturerId;
	private String make;
	
	public String getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	

}

	

