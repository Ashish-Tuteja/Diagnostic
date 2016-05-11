package com.auction.pro.user.dto;

import com.auction.pro.common.dto.BaseDTO;
import com.auction.pro.user.model.UserLog;

public class UserLogDto extends BaseDTO {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private String activity;
	private String userName;
	private String location;
	private Long date;

	public UserLogDto() {
		// TODO Auto-generated constructor stub
	}

	public UserLogDto(UserLog userLog) {
		// TODO Auto-generated constructor stub
		setId(userLog.getId());
		this.activity = userLog.getActivity();
		this.location = userLog.getLocation();
		this.userName = userLog.getUserName();
		this.date = userLog.getDate();
	}

	public UserLogDto(String user, String currentlocation, String activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
		this.location = currentlocation;
		this.userName = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

}
