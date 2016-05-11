package com.auction.pro.vehicle.model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.common.model.BaseModel;
import com.auction.pro.vehicle.dto.VehicleDto;

@Document(collection = "vehicleReport")
public class VehicleReport extends BaseModel implements
		Comparable<VehicleReport> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reportType;
	private Map<String, Object> report;
	private long timeStamp;
	private String reportgroupId;
	private String ip;
	private int packetType;
	@Transient
	private long reportCount;

	public VehicleReport() {
		// TODO Auto-generated constructor stub
	}

	public VehicleReport(VehicleDto dto) {
		// TODO Auto-generated constructor stub
		Map<String, Object> getReport = null;
		if (dto != null) {
			getReport = new HashMap<String, Object>();
			getReport.put(dto.getReportname(), dto.getReport());
		}
		this.reportType = !StringUtils.isEmpty(dto.getReportType()) ? dto
				.getReportType() : "";
		this.report = getReport.size() != 0 ? getReport : null;
		this.timeStamp = Calendar.getInstance().getTimeInMillis();
		System.out.println("timestamp in dto 0000000000000000000000000000000000000000000000000000000000"+this.timeStamp);
		this.reportType = dto.getReportname();
		this.reportgroupId = !StringUtils.isEmpty(dto.getReportgroupId()) ? dto
				.getReportgroupId() : UUID.randomUUID().toString();
		this.ip = !StringUtils.isEmpty(dto.getServerIP()) ? dto.getServerIP()
				: "No IP";
		this.packetType = dto.getPacketType();
		this.setCreatedAt(dto.getCreatedAt());
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Map<String, Object> getReport() {
		return report;
	}

	public void setReport(Map<String, Object> report) {
		this.report = report;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getReportgroupId() {
		return reportgroupId;
	}

	public void setReportgroupId(String reportgroupId) {
		this.reportgroupId = reportgroupId;
	}

	public long getReportCount() {
		return reportCount;
	}

	public void setReportCount(long reportCount) {
		this.reportCount = reportCount;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPacketType() {
		return packetType;
	}

	public void setPacketType(int packetType) {
		this.packetType = packetType;
	}

	/*
	 * generate reports in descending order
	 */
	public int compareTo(VehicleReport vReport) {
		// TODO Auto-generated method stub
		if (this.packetType == vReport.packetType) {
			return 0;
		} else if (this.packetType > vReport.packetType) {
			return -1;

		} else {
			return 1;
		}
	}

}
