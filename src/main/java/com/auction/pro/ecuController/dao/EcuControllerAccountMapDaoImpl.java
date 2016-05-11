package com.auction.pro.ecuController.dao;

import org.springframework.stereotype.Repository;

import com.auction.pro.common.dao.AbstractDAOImpl;
import com.auction.pro.ecuController.dao.base.EcuControllerAccountMapDao;
import com.auction.pro.ecuController.model.EcuControllerAccountMap;

@Repository
public class EcuControllerAccountMapDaoImpl extends AbstractDAOImpl<EcuControllerAccountMap>
		implements EcuControllerAccountMapDao {

	@Override
	public boolean isExists(EcuControllerAccountMap entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
