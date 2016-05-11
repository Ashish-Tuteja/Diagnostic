package com.auction.pro.vehicle.model;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.common.model.BaseModel;
import com.auction.pro.vehicle.dto.VehicleDto;

@Document(collection = "vehicle")
public class Vehicle extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vehicleId;
	private List<Map<String, Object>> report;
	private List<String> parentAccountId;
	private String vin;
	private String year;
	private String model;
	private String driverline;
	private boolean status;
	private String deviceId;
	private String deviceName;
	private String make;
	private String body;
	private String engine;
	private String trim;
	private List<Long> timestamp;
	private Set<String> reportgroupIds;

	public Vehicle() {
		// TODO Auto-generated constructor stub
	}

	public Vehicle(VehicleDto vehicleDto) {
		// TODO Auto-generated constructor stub
		super(vehicleDto);
		this.vehicleId = !StringUtils.isEmpty(vehicleDto.getVechileId()) ? vehicleDto
				.getVechileId() : "";
		this.vin = !StringUtils.isEmpty(vehicleDto.getVin()) ? vehicleDto
				.getVin() : "";
		this.year = !StringUtils.isEmpty(vehicleDto.getYear()) ? vehicleDto
				.getYear() : "";
		this.model = !StringUtils.isEmpty(vehicleDto.getModel()) ? vehicleDto
				.getModel() : "";
		this.driverline = !StringUtils.isEmpty(vehicleDto.getDriverline()) ? vehicleDto
				.getDriverline() : "";
		this.make = !StringUtils.isEmpty(vehicleDto.getMake()) ? vehicleDto
				.getMake() : "";
		this.body = !StringUtils.isEmpty(vehicleDto.getBody()) ? vehicleDto
				.getBody() : "";
		this.engine = !StringUtils.isEmpty(vehicleDto.getEngine()) ? vehicleDto
				.getEngine() : "";
		this.trim = !StringUtils.isEmpty(vehicleDto.getTrim()) ? vehicleDto
				.getTrim() : "";
		this.timestamp = Arrays
				.asList(Calendar.getInstance().getTimeInMillis());

		this.status = BooleanUtils.isFalse(vehicleDto.isStatus()) ? false
				: true;
		this.deviceId = !StringUtils.isEmpty(vehicleDto.getDeviceId()) ? vehicleDto
				.getDeviceId() : "";
		this.deviceName = !StringUtils.isEmpty(vehicleDto.getDeviceName()) ? vehicleDto
				.getDeviceName() : "";
		// this.parentAccountId = vehicleDto.getParentAccountId();
		this.setCreatedAt(vehicleDto.getCreatedAt());

	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getDriverline() {
		return driverline;
	}

	public void setDriverline(String driverline) {
		this.driverline = driverline;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getTrim() {
		return trim;
	}

	public void setTrim(String trim) {
		this.trim = trim;
	}

	public List<String> getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(List<String> parentAccountId) {
		this.parentAccountId = parentAccountId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public List<Map<String, Object>> getReport() {
		return report;
	}

	public void setReport(List<Map<String, Object>> report) {
		this.report = report;
	}

	public List<Long> getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(List<Long> timestamp) {
		this.timestamp = timestamp;
	}

	public Set<String> getReportgroupIds() {
		return reportgroupIds;
	}

	public void setReportgroupIds(Set<String> reportgroupIds) {
		this.reportgroupIds = reportgroupIds;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

}
