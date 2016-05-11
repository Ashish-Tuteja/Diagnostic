package com.auction.pro.user.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.account.dto.AccountDto;
import com.auction.pro.common.model.BaseModel;
import com.auction.pro.user.dto.UserDto;

@Document(collection = "accountusermap")
public class AccountUserMap extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accountId;
	private String userId;

	public AccountUserMap() {
		// TODO Auto-generated constructor stub
	}

	public AccountUserMap(UserDto dto) {
		// TODO Auto-generated constructor stub
		this.accountId = dto.getAccountId();
		this.userId = dto.getUserId();
		this.setCreatedAt(dto.getCreatedAt());

	}

	public AccountUserMap(AccountDto dto) {
		// TODO Auto-generated constructor stub
		this.accountId = dto.getId();
		this.userId = dto.getUserId();
		this.setCreatedAt(dto.getCreatedAt());
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
