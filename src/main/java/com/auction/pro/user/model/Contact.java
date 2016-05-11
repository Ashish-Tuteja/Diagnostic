package com.auction.pro.user.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.account.dto.AccountDto;
import com.auction.pro.common.model.BaseModel;
import com.auction.pro.user.dto.UserDto;

@Document(collection = "contact")
public class Contact extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String officeNo;
	private String officeActivation;
	private String mobileNo;
	private String homePhone;
	private String mobileActivation;
	private String companyName;
	private String website;
	private String city;
	private String state;
	private String zip;
	private String status;
	private String accountType;
	private String country;

	public Contact() {
		// TODO Auto-generated constructor stub
	}

	public String getOfficeNo() {
		return officeNo;
	}

	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}

	public String getOfficeActivation() {
		return officeActivation;
	}

	public void setOfficeActivation(String officeActivation) {
		this.officeActivation = officeActivation;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getMobileActivation() {
		return mobileActivation;
	}

	public void setMobileActivation(String mobileActivation) {
		this.mobileActivation = mobileActivation;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Contact(UserDto dto) {
		// TODO Auto-generated constructor stub
		setId(dto.getAddress().getId());
		this.officeNo = dto.getAddress().getOfficeNo();
		this.officeActivation = dto.getAddress().getOfficeActivation();
		this.mobileNo = dto.getAddress().getMobileNo();
		this.mobileActivation = dto.getAddress().getMobileActivation();
		this.homePhone = dto.getAddress().getHomePhone();
		this.companyName = dto.getAddress().getCompanyName();
		this.website = dto.getAddress().getWebsite();
		this.city = dto.getAddress().getCity();
		this.state = dto.getAddress().getState();
		this.zip = dto.getAddress().getZip();
		this.country = dto.getAddress().getCountry();
		this.setCreatedAt(dto.getAddress().getCreatedAt());
	}

	public Contact(AccountDto dto) {
		// TODO Auto-generated constructor stub
		setId(dto.getContactId());
		this.officeNo = dto.getAddress().getOfficeNo();
		this.officeActivation = dto.getAddress().getOfficeActivation();
		this.mobileNo = dto.getAddress().getMobileNo();
		this.mobileActivation = dto.getAddress().getMobileActivation();
		this.homePhone = dto.getAddress().getHomePhone();
		this.companyName = dto.getAddress().getCompanyName();
		this.website = dto.getAddress().getWebsite();
		this.city = dto.getAddress().getCity();
		this.state = dto.getAddress().getState();
		this.zip = dto.getAddress().getZip();
		this.country = dto.getAddress().getCountry();
		this.setCreatedAt(dto.getAddress().getCreatedAt());

	}

}
