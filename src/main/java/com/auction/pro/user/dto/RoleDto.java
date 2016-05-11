package com.auction.pro.user.dto;

import java.util.List;

import com.auction.pro.common.dto.BaseDTO;

public class RoleDto extends BaseDTO {

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
