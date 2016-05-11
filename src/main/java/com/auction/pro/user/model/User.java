package com.auction.pro.user.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.auction.pro.common.model.BaseModel;
import com.auction.pro.user.dto.UserDto;

@Document(collection = "user")
public class User extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Field("username")
	private String userName;
	private String password;
	private String cnfpassword;
	@Field("emailid")
	private String emailId;
	private String firstName;
	private String lastName;
	@Transient
	private boolean status;
	private String loginAttempts;
	private String roleId;
	private String contactId;
	private List<String> userPermissions;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(UserDto userDto) {
		// TODO Auto-generated constructor stub
		super(userDto);
		this.userName = !StringUtils.isEmpty(userDto.getUsername()) ? userDto
				.getUsername() : "";
		this.password = !StringUtils.isEmpty(userDto.getPassword()) ? userDto
				.getPassword() : "";
		this.cnfpassword = !StringUtils.isEmpty(userDto.getCnfPassword()) ? userDto
				.getCnfPassword() : "";
		this.emailId = !StringUtils.isEmpty(userDto.getEmailId()) ? userDto
				.getEmailId() : "";
		this.firstName = !StringUtils.isEmpty(userDto.getFirstName()) ? userDto
				.getFirstName() : "";
		this.lastName = !StringUtils.isEmpty(userDto.getLastName()) ? userDto
				.getLastName() : "";
		// this.status = false;
		this.loginAttempts = String.valueOf(0);
		this.contactId = !StringUtils.isEmpty(userDto.getContactId()) ? userDto
				.getContactId() : "";
		this.roleId = !StringUtils.isEmpty(userDto.getRoleId()) ? userDto
				.getRoleId() : "";
		this.userPermissions = userDto.getRolePermission();
		this.setCreatedAt(userDto.getCreatedAt());

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCnfpassword() {
		return cnfpassword;
	}

	public void setCnfpassword(String cnfpassword) {
		this.cnfpassword = cnfpassword;
	}

	public String getEmailid() {
		return emailId;
	}

	public void setEmailid(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(String loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public List<String> getUserPermissions() {
		return userPermissions;
	}

	public void setUserPermissions(List<String> userPermissions) {
		this.userPermissions = userPermissions;
	}

}
