package com.auction.pro.sim.dao.base;

import java.util.List;

import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.sim.dto.SimDTO;
import com.auction.pro.sim.model.Sim;



public interface SimDao extends AbstractDAO<Sim>{
	
	public SimDTO findBySimNoOrMsisdnOrId(String value);
}
