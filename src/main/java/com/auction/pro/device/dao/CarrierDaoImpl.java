package com.auction.pro.device.dao;

import org.springframework.stereotype.Repository;

import com.auction.pro.common.dao.AbstractDAOImpl;
import com.auction.pro.device.dao.base.CarrierDao;
import com.auction.pro.device.model.Carrier;

@Repository
public class CarrierDaoImpl extends AbstractDAOImpl<Carrier> implements
		CarrierDao {

	@Override
	public boolean isExists(Carrier entity) {
		// TODO Auto-generated method stub
		return false;
	}
}
