package com.auction.pro.parameters.service.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auction.pro.common.service.AbstractService;
import com.auction.pro.parameters.dto.ParametersDto;

public interface ParametersService extends AbstractService<ParametersDto> {
	
	
	List<ParametersDto> findBySerachterm(String contId,String searchterm, Serializable accountId)
			throws Exception;


	List<ParametersDto> getParameters() throws Exception;


	public Page<ParametersDto> findAllPage(Pageable pageable, String parentAccountId)
			throws Exception;
	
	public Page<ParametersDto> findAllPageParameters(String id ,Pageable pageable, String parentAccountId)
			throws Exception;

	void delete(String id);
	
	void deleteParams(String id); 
	

}
