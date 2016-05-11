package com.auction.pro.account.model;

import com.auction.pro.common.model.BaseModel;

public class AccountType extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accountType;

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}
