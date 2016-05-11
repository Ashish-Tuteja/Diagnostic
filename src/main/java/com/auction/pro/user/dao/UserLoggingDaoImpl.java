package com.auction.pro.user.dao;

import org.springframework.stereotype.Repository;

import com.auction.pro.common.dao.AbstractDAOImpl;
import com.auction.pro.user.dao.base.UserLoggingDao;
import com.auction.pro.user.model.UserLog;

@Repository
public class UserLoggingDaoImpl extends AbstractDAOImpl<UserLog> implements
		UserLoggingDao {

	@Override
	public boolean isExists(UserLog entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
