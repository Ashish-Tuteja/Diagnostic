package com.auction.pro.vehicle.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.common.constants.NavResearchConstants;
import com.auction.pro.common.model.BaseModel;

// vehicle ecu list
@Document(collection = "ecu")
public class Ecu extends BaseModel implements NavResearchConstants {

	private static final long serialVersionUID = 1L;
	private String make;
	private String model;
	private String year;
	private List<EcuControllers> controllers;

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<EcuControllers> getControllers() {
		return controllers;
	}

	public void setControllers(List<EcuControllers> controllers) {
		this.controllers = controllers;
	}

	public static Ecu setVehicleECU(List<String> excelData,
			List<EcuControllers> ecuControllers) {
		String startYear;
		Ecu vehicleecu = new Ecu();
		vehicleecu.setMake(!StringUtils.isEmpty(excelData.get(0)) ? excelData
				.get(0) : "");
		vehicleecu.setModel(!StringUtils.isEmpty(excelData.get(1)) ? excelData
				.get(1) : "");
		startYear = excelData.get(2);
		vehicleecu.setYear(!StringUtils.isEmpty(startYear) ? startYear : "");
		vehicleecu.setControllers(ecuControllers);

		return vehicleecu;
	}
}
