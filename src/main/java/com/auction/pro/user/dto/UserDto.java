package com.auction.pro.user.dto;

import java.util.List;
import java.util.Map;

import com.auction.pro.common.dto.BaseDTO;
import com.auction.pro.user.model.Contact;
import com.auction.pro.user.model.User;

public class UserDto extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String accountId;
	private String accountActivationId;
	private String parentAccountId;
	private List<String> parentAccountIds;
	private String accountName;
	private String contactId;
	private String username;
	private String password;
	private String cnfPassword;
	private String emailId;
	private String firstName;
	private String status;
	private String accountType;
	private String activationId;
	private String lastName;
	private String roleId;
	private List<RoleDto> roles;
	private List<String> rolePermission;
	private List<String> allPermissions;
	private String country;
	private Contact address;
	private Map<String, Boolean> permissionsMap;
	private String passwordCode;

	public UserDto() {
		// TODO Auto-generated constructor stub
	}

	public UserDto(User userEntity) {
		super(userEntity);
		setId(userEntity.getId());
		this.username = userEntity.getUserName();
		this.emailId = userEntity.getEmailid();
		this.firstName = userEntity.getFirstName();
		this.lastName = userEntity.getLastName();
		this.status = String.valueOf(userEntity.isStatus());
		this.contactId = userEntity.getContactId();
		this.roleId = userEntity.getRoleId();
		this.rolePermission = userEntity.getUserPermissions();

	}

	public UserDto(Contact address) {
		this.address.setCity(address.getCity());
		this.address.setOfficeNo(address.getOfficeNo());
		this.address.setOfficeActivation(address.getOfficeActivation());
		this.address.setMobileNo(address.getMobileNo());
		this.address.setHomePhone(address.getHomePhone());
		this.address.setMobileActivation(address.getMobileActivation());
		this.address.setCompanyName(address.getCompanyName());
		this.address.setWebsite(address.getWebsite());
		this.address.setState(address.getState());
		this.address.setZip(address.getZip());
		this.address.setStatus(address.getStatus());
		this.address.setCountry(address.getCountry());

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCnfPassword() {
		return cnfPassword;
	}

	public void setCnfPassword(String cnfPassword) {
		this.cnfPassword = cnfPassword;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public Contact getAddress() {
		return address;
	}

	public void setAddress(Contact address) {
		this.address = address;
	}

	public String getActivationId() {
		return activationId;
	}

	public void setActivationId(String activationId) {
		this.activationId = activationId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(String parentAccountId) {
		this.parentAccountId = parentAccountId;
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

	public String getAccountActivationId() {
		return accountActivationId;
	}

	public void setAccountActivationId(String accountActivationId) {
		this.accountActivationId = accountActivationId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public List<String> getRolePermission() {
		return rolePermission;
	}

	public void setRolePermission(List<String> rolePermission) {
		this.rolePermission = rolePermission;
	}

	public List<String> getAllPermissions() {
		return allPermissions;
	}

	public void setAllPermissions(List<String> allPermissions) {
		this.allPermissions = allPermissions;
	}

	public Map<String, Boolean> getPermissionsMap() {
		return permissionsMap;
	}

	public void setPermissionsMap(Map<String, Boolean> permissionsMap) {
		this.permissionsMap = permissionsMap;
	}

	public List<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}

	public String getPasswordCode() {
		return passwordCode;
	}

	public void setPasswordCode(String passwordCode) {
		this.passwordCode = passwordCode;
	}

	public List<String> getParentAccountIds() {
		return parentAccountIds;
	}

	public void setParentAccountIds(List<String> parentAccountIds) {
		this.parentAccountIds = parentAccountIds;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
