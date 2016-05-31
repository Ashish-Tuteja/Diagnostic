package com.auction.pro.parameters.dao.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.ecuController.model.EcuController;
import com.auction.pro.parameters.model.Parameters;

public interface ParametersDao extends AbstractDAO<Parameters> {
	List<Parameters> findBySerachterm(String contId,String searchterm, Serializable accountId)
			throws Exception;


	public List<Parameters> getParameters() throws Exception;
	
	public String getParamDescIdByControllerId(String id) throws Exception;

	public Page<Parameters> findAllPage(Pageable pageable, String parentAccountId)
			throws Exception;
	
	public Page<Parameters> findAllPageParameters(String id ,Pageable pageable, String parentAccountId)
			throws Exception;

	Parameters findByParametersIp(String ip);
	
	void deleteParams(Parameters params);
}
