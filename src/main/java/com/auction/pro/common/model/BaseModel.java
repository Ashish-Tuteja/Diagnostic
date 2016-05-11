package com.auction.pro.common.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.auction.pro.common.dto.BaseDTO;

public class BaseModel extends BaseAudit implements Serializable {

	private static final long serialVersionUID = 3809460713572529038L;
	@Id
	@Field("_id")
	private String id;

	public BaseModel() {
	}

	public BaseModel(BaseDTO model) {
		if (model != null) {
			this.id = model.getId();
		}
	}

	public BaseModel(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
