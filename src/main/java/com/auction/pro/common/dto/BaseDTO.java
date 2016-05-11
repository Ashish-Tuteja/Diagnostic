package com.auction.pro.common.dto;

import java.io.Serializable;

import com.auction.pro.common.model.BaseAudit;
import com.auction.pro.common.model.BaseModel;

public class BaseDTO extends BaseAudit implements Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = -3626413296223258421L;
	private String id;

	public BaseDTO() {
	}

	public BaseDTO(BaseModel model) {
		if (model != null) {
			this.id = model.getId();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
