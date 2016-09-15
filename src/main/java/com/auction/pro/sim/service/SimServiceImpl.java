package com.auction.pro.sim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.common.service.AbstractServiceImpl;
import com.auction.pro.sim.dao.base.SimDao;
import com.auction.pro.sim.dto.SimDTO;
import com.auction.pro.sim.model.Sim;
import com.auction.pro.sim.service.base.SimService;
@Service
public class SimServiceImpl extends AbstractServiceImpl<SimDTO, Sim> implements SimService{
	@Autowired
	SimDao simDao;
	public SimDTO save(SimDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(SimDTO entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractDAO<Sim> getDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SimDTO getDTOForEntity(Sim entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getEntityFromDTO(SimDTO entity, Class classType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SimDTO> getDTOsForEntities(Iterable<Sim> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	public SimDTO findBySimNoOrMsisdnOrId(String value) {
		// TODO Auto-generated method stub
		
		return simDao.findBySimNoOrMsisdnOrId(value);
		
	}

}
