package com.auction.pro.user.dto;

import com.auction.pro.common.dto.BaseDTO;

public class AccountUserMapDto extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accountId;
	private String userId;

	public AccountUserMapDto() {
		// TODO Auto-generated constructor stub
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
