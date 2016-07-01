package com.auction.pro.ecuController.dto;

import java.util.List;

import com.auction.pro.common.dto.BaseDTO;
import com.auction.pro.ecuController.model.EcuController;

public class EcuControllerDto extends BaseDTO {

	private static final long serialVersionUID = 1L;
	private List<String> parentAccountId;
	private String make;
	private String model;
	private String year;
	// private String description;
	private String controllerName;
	private String controllerId;

	public EcuControllerDto() {
		// TODO Auto-generated constructor stub
	}

	public EcuControllerDto(EcuController entity) {
		// TODO Auto-generated constructor stub
		if (entity != null) {
			setId(entity.getId());
			this.make = entity.getMake();
			this.model = entity.getModel();
			this.year = entity.getYear();
			this.controllerName = entity.getControllerName();
			this.controllerId = entity.getControllerId();
			this.parentAccountId = entity.getParentAccountId();
			setCreatedAt(entity.getCreatedAt());
			
		}
	}

	public List<String> getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(List<String> parentAccountId) {
		this.parentAccountId = parentAccountId;
	}

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

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public String getControllerId() {
		return controllerId;
	}

	public void setControllerId(String controllerId) {
		this.controllerId = controllerId;
	}


}
