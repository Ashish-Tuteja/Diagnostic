package com.auction.pro.sim.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.common.model.BaseModel;

@Document(collection = "device_sim")
public class Sim extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String device_id ;
	private String sim;
	private String msisdn;
	private String ip_address;
	private String is_activated;
	private String date_created;
	private String date_updated;
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getSim() {
		return sim;
	}
	public void setSim(String sim) {
		this.sim = sim;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getIs_activated() {
		return is_activated;
	}
	public void setIs_activated(String is_activated) {
		this.is_activated = is_activated;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getDate_updated() {
		return date_updated;
	}
	public void setDate_updated(String date_updated) {
		this.date_updated = date_updated;
	}


	
}
