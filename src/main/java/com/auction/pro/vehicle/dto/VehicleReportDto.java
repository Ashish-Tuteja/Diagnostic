package com.auction.pro.vehicle.dto;

import com.auction.pro.common.dto.BaseDTO;
import com.auction.pro.vehicle.model.VehicleReport;

public class VehicleReportDto extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String report;
	private String reportname;
	private long timeStamp;
	private String reportgroupId;
	private long reportCount;

	public VehicleReportDto() {
		// TODO Auto-generated constructor stub
	}

	public VehicleReportDto(VehicleReport report) {
		// TODO Auto-generated constructor stub
		this.timeStamp = report.getTimeStamp();
		this.reportCount = report.getReportCount();
		this.reportgroupId = report.getReportgroupId();

	}

	public String getReportname() {
		return reportname;
	}

	public void setReportname(String reportname) {
		this.reportname = reportname;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
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

}
