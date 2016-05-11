package com.auction.pro.vehicle.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.common.model.BaseModel;
import com.auction.pro.vehicle.dto.VehicleDto;

@Document(collection = "devicevehiclemap")
public class DeviceVehicleMap extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deviceId;
	private String vehicleId;

	public DeviceVehicleMap() {
		// TODO Auto-generated constructor stub
	}

	public DeviceVehicleMap(VehicleDto dto) {
		// TODO Auto-generated constructor stub
		this.deviceId = dto.getDeviceId();
		this.vehicleId = dto.getId();
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
