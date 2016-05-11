package com.auction.pro.ecuController.dao.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.ecuController.model.EcuController;

public interface EcuControllerDao extends AbstractDAO<EcuController> {
	List<EcuController> findBySerachterm(String searchterm, Serializable accountId)
			throws Exception;

//	List<Carrier> getCarriers() throws Exception;

//	List<EcuControllerType> getEcuControllerTypes() throws Exception;

	public List<EcuController> getEcuControllers() throws Exception;

	public Page<EcuController> findAllPage(Pageable pageable, String parentAccountId)
			throws Exception;

	EcuController findByEcuControllerIp(String ip);
}
