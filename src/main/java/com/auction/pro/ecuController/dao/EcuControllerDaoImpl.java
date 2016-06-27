package com.auction.pro.ecuController.dao;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.auction.pro.common.constants.MongoConstant;
import com.auction.pro.common.dao.AbstractDAOImpl;
import com.auction.pro.device.model.Carrier;
import com.auction.pro.ecuController.dao.base.EcuControllerDao;
import com.auction.pro.ecuController.model.EcuController;
import com.mongodb.QueryBuilder;

@Repository
public class EcuControllerDaoImpl extends AbstractDAOImpl<EcuController>
		implements EcuControllerDao, MongoConstant {
	@Autowired
	MongoTemplate mongoTemplate;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EcuControllerDaoImpl.class.getName());

	public List<EcuController> findBySerachterm(String searchterm,
			Serializable parentAccountId) throws Exception {
		// TODO Auto-generated method stub
		return mongoTemplate.find(
				new Query(new Criteria().orOperator(
						Criteria.where("controllerName").regex(searchterm),
						Criteria.where("make").regex(searchterm), Criteria
								.where("model").regex(searchterm), Criteria
								.where("year").regex(searchterm))),
				EcuController.class);

	}

	public List<Carrier> getCarriers() throws Exception {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(Carrier.class);
	}

	// public List<EcuControllerType> getEcuControllerTypes() throws Exception {
	// // TODO Auto-generated method stub
	// return mongoTemplate.findAll(EcuControllerType.class);
	// }

	public List<EcuController> getEcuControllers() throws Exception {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(EcuController.class);
	}

	@Override
	public void delete(EcuController entity) {
		Query query = new Query(Criteria.where("_id").is(entity.getId()));
		mongoTemplate.remove(query, "ecu_controllers");
	}

	@Override
	public boolean isExists(EcuController entity) {
		// TODO Auto-generated method stub
		try {
			return (mongoTemplate.findOne(
					new Query(new Criteria().orOperator(Criteria.where(
							"controllerId").is(entity.getControllerId()))),
					EcuController.class).getControllerId() != null) ? true
					: false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public Page<EcuController> findAllPage(Pageable pageable,
			String parentAccountId) throws Exception {
		// TODO Auto-generated method stub
		List<EcuController> list = null;
		Query query = new Query();
		/*
		 * query.addCriteria(Criteria.where("parentAccountId")
		 * .all(parentAccountId));
		 */
		query.with(pageable);
		list = mongoTemplate.find(query, EcuController.class);
		Page<EcuController> entityPage = new PageImpl<EcuController>(list,
				pageable, mongoTemplate.count(
				/*
				 * Query.query(Criteria.where("parentAccountId").all(
				 * parentAccountId))
				 */new Query(), EcuController.class));
		return entityPage;
	}

	public EcuController findByEcuControllerIp(String id) {
		// TODO Auto-generated method stub
		return mongoTemplate.findOne(
				Query.query(Criteria.where("controllerId").is(id)),
				EcuController.class);
	}

}
