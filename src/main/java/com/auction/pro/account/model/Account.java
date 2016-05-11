package com.auction.pro.account.model;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.account.dto.AccountDto;
import com.auction.pro.common.model.BaseModel;
import com.auction.pro.common.utils.CommonUtils;
import com.auction.pro.user.dto.UserDto;
import com.google.gson.Gson;

@Document(collection = "account")
public class Account extends BaseModel {
	private static final long serialVersionUID = 1L;

	private List<String> parentAccountId;
	private String activationId;
	private String accountName;
	private boolean status;
	private String contactId;

	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(AccountDto accountDto) {
		// TODO Auto-generated constructor stub
		setId(accountDto.getId());
		this.accountName = !StringUtils.isEmpty(accountDto.getAccountName()) ? accountDto
				.getAccountName() : "";
		this.contactId = !StringUtils.isEmpty(accountDto.getContactId()) ? accountDto
				.getContactId() : "";
		this.status = BooleanUtils.isFalse(accountDto.isStatus()) ? false
				: true;
		this.activationId = CommonUtils.generateUUID();
		this.parentAccountId = (accountDto.getParentAccountIds() == null) ? Arrays
				.asList("12-35463-374484-3738356636-373737") : accountDto
				.getParentAccountIds();
		this.setCreatedAt(accountDto.getCreatedAt());
		
	}

	public Account(UserDto userDto) {
		// TODO Auto-generated constructor stub
		this.setParentAccountId((userDto.getParentAccountId() == "" || userDto
				.getParentAccountId() == null) ? Arrays
				.asList("12-35463-374484-3738356636-373737") : userDto
				.getParentAccountIds());
		this.accountName = userDto.getUsername();
		this.setContactId(userDto.getContactId());
		this.setStatus(false);
		this.activationId = CommonUtils.generateUUID();
	}

	public String getActivationId() {
		return activationId;
	}

	public void setActivationId(String activationId) {
		this.activationId = activationId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<String> getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(List<String> parentAccountId) {
		this.parentAccountId = parentAccountId;
	}

}
