package com.auction.pro.user.dao.base;

import org.json.JSONException;

import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.user.model.User;

public interface UserLoginDao extends AbstractDAO<User> {
	public User findByUsername(User user) throws JSONException;

	public int enableLoginAttempts(String user);

	public int getLoginAttempts(String user);

	public void disableLoginAttempts(String user);
}
