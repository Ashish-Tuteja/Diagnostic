package com.auction.pro.ecuController.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.common.model.BaseModel;
import com.auction.pro.common.utils.CommonUtils;
import com.auction.pro.ecuController.dto.EcuControllerDto;

@Document(collection = "ecu_controllers")
public class EcuController extends BaseModel {

	/**
	 * 	Model class of Ecu Controller
	 */
	private static final long serialVersionUID = 1L;
	private String make;
	private String model;
	private String year;
	// private String description;
	private String controllerName;
	private String controllerId;
	private List<String> parentAccountId;

	public List<String> getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(List<String> parentAccountId) {
		this.parentAccountId = parentAccountId;
	}

	public EcuController() {
		// TODO Auto-generated constructor stub
	}

	public EcuController(EcuControllerDto ecuControllerDto) {
		// TODO Auto-generated constructor stub
		super(ecuControllerDto);
		// this.parentAccountId = ecuControllerDto.getParentAccountId();
		this.make = !StringUtils.isEmpty(ecuControllerDto.getMake()) ? ecuControllerDto
				.getMake() : "";
		this.model = !StringUtils.isEmpty(ecuControllerDto.getModel()) ? ecuControllerDto
				.getModel() : "";
		this.year = !StringUtils.isEmpty(ecuControllerDto.getYear()) ? ecuControllerDto
				.getYear() : "";
		this.controllerName = !StringUtils.isEmpty(ecuControllerDto.getControllerName()) ? ecuControllerDto
				.getControllerName() : "";
		this.controllerId = !StringUtils.isEmpty(ecuControllerDto.getControllerId()) ? ecuControllerDto
				.getControllerId() : CommonUtils.generateUUID();
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
