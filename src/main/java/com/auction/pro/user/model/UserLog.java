package com.auction.pro.user.model;

import java.util.Calendar;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.common.model.BaseModel;
import com.auction.pro.user.dto.UserLogDto;

@Document(collection = "userlog")
public class UserLog extends BaseModel {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private String activity;
	private String userName;
	private String location;
	private Long date;

	public UserLog() {
		// TODO Auto-generated constructor stub
	}

	public UserLog(UserLogDto userLogDto) {
		// TODO Auto-generated constructor stub
		this.activity = userLogDto.getActivity();
		this.location = userLogDto.getLocation();
		this.userName = userLogDto.getUserName();
		this.date = Calendar.getInstance().getTimeInMillis();
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
