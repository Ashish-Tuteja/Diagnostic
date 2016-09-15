package com.auction.pro.sim.service.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auction.pro.common.service.AbstractService;
import com.auction.pro.sim.dto.SimDTO;

public interface SimService extends AbstractService<SimDTO>{

	public SimDTO save(SimDTO entity);

	public void delete(SimDTO entity);	

	public SimDTO findById(Serializable id);

	public void deleteById(Serializable id) ;

	public Page<SimDTO> findAllPage(Pageable pageable, List<String> fields) ;

	public Page<SimDTO> findAllPage(Pageable pageable) ;

	public List<SimDTO> findAll();

	public List<SimDTO> findAll(List<String> fields);

	public SimDTO findBySimNoOrMsisdnOrId(String value);
	
}
