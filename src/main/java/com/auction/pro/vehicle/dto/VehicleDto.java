package com.auction.pro.vehicle.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.BooleanUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.auction.pro.common.dto.BaseDTO;
import com.auction.pro.vehicle.model.Vehicle;

public class VehicleDto extends BaseDTO {
	private static final long serialVersionUID = 1L;
	private List<String> parentAccountId;
	private String vehicleId;
	private String vin;
	private String year;
	private String reportType;
	private String model;
	private String driverline;
	private boolean status;
	private String deviceId;
	private String make;
	private String body;
	private String engine;
	private String trim;
	private String deviceName;
	private String report;
	private String reportname;
	private String serverIP;
	private List<Map<String, Object>> responseReport;
	private Date timestamp;
	private String reportgroupId;
	private Set<String> reportgroupIds;
	private int totalReports;
	private int packetType;

	public VehicleDto() {
		// TODO Auto-generated constructor stub
	}

	public VehicleDto(Vehicle vehicle) {
		// TODO Auto-generated constructor stub
		setId(vehicle.getId());
		this.vin = vehicle.getVin();
		this.setYear(vehicle.getYear());
		this.model = vehicle.getModel();
		this.driverline = vehicle.getDriverline();
		this.status = vehicle.isStatus();
		this.make = vehicle.getMake();
		this.body = vehicle.getBody();
		this.engine = vehicle.getEngine();
		this.trim = vehicle.getTrim();
		// this.parentAccountId = vehicle.getParentAccountId();
		this.deviceId = vehicle.getDeviceId();
		this.deviceName = vehicle.getDeviceName();
		this.timestamp = new Date(vehicle.getTimestamp().get(
				vehicle.getTimestamp().size() - 1));
		this.totalReports = vehicle.getReportgroupIds() != null ? vehicle
				.getReportgroupIds().size() : 0;
	}

	public VehicleDto(JSONObject json) throws JSONException {
		// TODO Auto-generated constructor stub
		JSONObject vehicleJSON = null;
		if (json.get("vehicle") instanceof JSONArray) {
			vehicleJSON = ((JSONArray) json.get("vehicle")).getJSONObject(0);

		} else {
			vehicleJSON = json.getJSONObject("vehicle");
		}
		if (vehicleJSON == null) {
			System.out.println("Response JSON not parse");
		} else {
			this.vehicleId = vehicleJSON.get("id").toString();
			this.model = vehicleJSON.get("model").toString();
			this.vin = json.get("VIN").toString();
			this.driverline = vehicleJSON.get("driveline").toString();
			this.make = vehicleJSON.get("make").toString();
			this.body = vehicleJSON.get("body").toString();
			this.engine = vehicleJSON.get("engine").toString();
			this.trim = vehicleJSON.get("trim").toString();
			this.year = vehicleJSON.get("year").toString();
			this.status = BooleanUtils.toBoolean(json.get("Valid").toString());

		}

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

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getVechileId() {
		return vehicleId;
	}

	public void setVechileId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getReportname() {
		return reportname;
	}

	public void setReportname(String reportname) {
		this.reportname = reportname;
	}

	public List<Map<String, Object>> getResponseReport() {
		return responseReport;
	}

	public void setResponseReport(List<Map<String, Object>> responseReport) {
		this.responseReport = responseReport;
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getReportgroupId() {
		return reportgroupId;
	}

	public void setReportgroupId(String reportgroupId) {
		this.reportgroupId = reportgroupId;
	}

	public Set<String> getReportgroupIds() {
		return reportgroupIds;
	}

	public void setReportgroupIds(Set<String> reportgroupIds) {
		this.reportgroupIds = reportgroupIds;
	}

	public int getTotalReports() {
		return totalReports;
	}

	public void setTotalReports(int totalReports) {
		this.totalReports = totalReports;
	}

	public int getPacketType() {
		return packetType;
	}

	public void setPacketType(int packetType) {
		this.packetType = packetType;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String urloutput = getVechileId() + " " + getModel() + " " + getVin()
				+ " " + getDriverline() + " " + getMake() + " " + getBody()
				+ " " + getEngine() + " " + getTrim() + " " + getYear() + " "
				+ isStatus();
		return urloutput;
	}
}
