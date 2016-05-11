package com.auction.pro.device.dao;

import org.springframework.stereotype.Repository;

import com.auction.pro.common.dao.AbstractDAOImpl;
import com.auction.pro.device.dao.base.DeviceAccountMapDao;
import com.auction.pro.device.model.DeviceAccountMap;

@Repository
public class DeviceAccountMapDaoImpl extends AbstractDAOImpl<DeviceAccountMap>
		implements DeviceAccountMapDao {

	@Override
	public boolean isExists(DeviceAccountMap entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
