package com.auction.pro.vehicle.dao;

import org.springframework.stereotype.Repository;

import com.auction.pro.common.dao.AbstractDAOImpl;
import com.auction.pro.vehicle.dao.base.DeviceVehicleMapDao;
import com.auction.pro.vehicle.model.DeviceVehicleMap;

@Repository
public class DeviceVehicleMapImpl extends AbstractDAOImpl<DeviceVehicleMap>
		implements DeviceVehicleMapDao {

	@Override
	public boolean isExists(DeviceVehicleMap entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
