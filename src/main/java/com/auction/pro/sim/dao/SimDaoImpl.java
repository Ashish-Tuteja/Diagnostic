package com.auction.pro.sim.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.auction.pro.common.constants.MongoConstant;
import com.auction.pro.common.constants.NavResearchConstants;
import com.auction.pro.common.dao.AbstractDAOImpl;
import com.auction.pro.sim.dao.base.SimDao;
import com.auction.pro.sim.dto.SimDTO;
import com.auction.pro.sim.model.Sim;
@Repository
public class SimDaoImpl extends AbstractDAOImpl<Sim> implements SimDao, MongoConstant, NavResearchConstants{

	@Autowired
	MongoTemplate mongoTemplate;
	@Override
	public boolean isExists(Sim entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public SimDTO findBySimNoOrMsisdnOrId(String value){
		Sim sim=null;
		SimDTO simDTO=null;
		
		try{
			Criteria or = new Criteria().orOperator(
		    Criteria.where("device_id").is(value), Criteria
                                .where("msisdn").is(value), Criteria
                                .where("sim").is(value));
		sim = mongoTemplate.findOne(Query.query(or), Sim.class);
		if(sim !=null){
			simDTO=new SimDTO(sim);
		}
		}catch(Exception we){
			
			we.printStackTrace();
		}
		return simDTO;
	}
}
