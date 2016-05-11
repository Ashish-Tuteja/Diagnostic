package com.auction.pro.user.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.common.model.BaseModel;

@Document(collection = "session")
public class Session extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private Date startDate;
	private Date lastHit;
	private boolean active;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getLastHit() {
		return lastHit;
	}

	public void setLastHit(Date lastHit) {
		this.lastHit = lastHit;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
