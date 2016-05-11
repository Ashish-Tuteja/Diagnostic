package com.auction.pro.user.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.common.model.BaseModel;

@Document(collection = "role")
public class Role extends BaseModel {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private String name;
	private List<String> permissionId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(List<String> permissionId) {
		this.permissionId = permissionId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
