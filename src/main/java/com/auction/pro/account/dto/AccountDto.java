package com.auction.pro.account.dto;

import java.util.List;

import com.auction.pro.account.model.Account;
import com.auction.pro.common.dto.BaseDTO;
import com.auction.pro.user.model.Contact;

public class AccountDto extends BaseDTO {
	private static final long serialVersionUID = 1L;
	private String parentAccountId;
	private String contactId;
	private String accountName;
	private Contact address;
	private boolean status;
	private String activationId;
	private String userId;
	private List<String> parentAccountIds;

	public AccountDto() {
		// TODO Auto-generated constructor stub
	}

	public AccountDto(Account account) {
		setId(account.getId());
		this.accountName = account.getAccountName();
		this.status = account.isStatus();
		this.contactId = account.getContactId();
		this.activationId = account.getActivationId();
		this.parentAccountIds = account.getParentAccountId();
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Contact getAddress() {
		return address;
	}

	public void setAddress(Contact address) {
		this.address = address;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getActivationId() {
		return activationId;
	}

	public void setActivationId(String activationId) {
		this.activationId = activationId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(String parentAccountId) {
		this.parentAccountId = parentAccountId;
	}

	public List<String> getParentAccountIds() {
		return parentAccountIds;
	}

	public void setParentAccountIds(List<String> parentAccountIds) {
		this.parentAccountIds = parentAccountIds;
	}

}
