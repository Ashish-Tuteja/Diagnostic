package com.auction.pro.common.model;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseAudit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String createdBy;
	private Long createdAt;
	private String lastModifiedBy;
	private Long lastmodified;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Long getLastmodified() {
		return lastmodified;
	}

	public void setLastmodified(Long lastmodified) {
		this.lastmodified = lastmodified;
	}

}
