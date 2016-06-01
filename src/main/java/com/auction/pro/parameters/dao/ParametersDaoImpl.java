package com.auction.pro.parameters.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
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
import com.auction.pro.parameters.dao.base.ParametersDao;
import com.auction.pro.parameters.model.Parameters;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

@Repository
public class ParametersDaoImpl extends AbstractDAOImpl<Parameters> implements
		ParametersDao, MongoConstant {
	@Autowired
	MongoTemplate mongoTemplate;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ParametersDaoImpl.class.getName());

	public List<Parameters> findBySerachterm(String contId, String searchterm,
			Serializable parentAccountId) throws Exception {
		// TODO Auto-generated method stub
		Query query = new Query();
		Criteria criteria = new Criteria();
		criteria.andOperator(Criteria.where("controllerId").regex(contId))
				.orOperator(Criteria.where("parameterDesc").regex(searchterm),
						Criteria.where("txId").regex(searchterm),
						Criteria.where("rxId").regex(searchterm),
						Criteria.where("isEnhanced").regex(searchterm),
						Criteria.where("serviceId").regex(searchterm),
						Criteria.where("parameterId").regex(searchterm),
						Criteria.where("length").regex(searchterm),
						Criteria.where("offset").regex(searchterm),
						Criteria.where("bitpostion").regex(searchterm),
						Criteria.where("bitwidth").regex(searchterm),
						Criteria.where("wasError").regex(searchterm));
		query.addCriteria(criteria);
		return mongoTemplate.find(query, Parameters.class);

	}

	public List<Carrier> getCarriers() throws Exception {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(Carrier.class);
	}

	public List<Parameters> getParameters() throws Exception {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(Parameters.class);
	}

	@Override
	public void delete(Parameters entity) {
		Query query = new Query(Criteria.where("_id").is(entity.getId()));
		mongoTemplate.remove(query, "parameter_tests");

	}

	@Override
	public void deleteParams(Parameters entity) {
		Query query = new Query(Criteria.where("controllerId").is(entity.getControllerId()));
		mongoTemplate.remove(query, "parameter_tests");

	}

	@Override
	public boolean isExists(Parameters entity) {
		// TODO Auto-generated method stub
		try {
			return (mongoTemplate.findOne(
					new Query(new Criteria().orOperator(Criteria.where(
							"controllerId").is(entity.getControllerId()))),
					Parameters.class).getControllerId() != null) ? true : false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public Page<Parameters> findAllPage(Pageable pageable,
			String parentAccountId) throws Exception {
		// TODO Auto-generated method stub
		List<Parameters> list = null;
		Query query = new Query();
		query.with(pageable);
		list = mongoTemplate.find(query, Parameters.class);
		Page<Parameters> entityPage = new PageImpl<Parameters>(list, pageable,
				mongoTemplate.count(
				/*
				 * Query.query(Criteria.where("parentAccountId").all(
				 * parentAccountId))
				 */new Query(), Parameters.class));
		return entityPage;
	}

	public Page<Parameters> findAllPageParameters(String controllerId,
			Pageable pageable, String parentAccountId) throws Exception {
		// TODO Auto-generated method stub
		List<Parameters> list = null;
		Query query = new Query();

		query.with(pageable);
		query.addCriteria(Criteria.where("controllerId").is(controllerId));

		list = mongoTemplate.find(query, Parameters.class);
		LOGGER.info("No of parameters returned for controllerId" + controllerId
				+ ":::" + list.size());
		Page<Parameters> entityPage = new PageImpl<Parameters>(list, pageable,
				mongoTemplate.count(
				/*
				 * Query.query(Criteria.where("parentAccountId").all(
				 * parentAccountId))
				 */new Query(), Parameters.class));
		return entityPage;
	}

	public String getParamDescIdByControllerId(String id) {

		// Sorting on the basis of ParameterDescID
		DBCollection collection = mongoTemplate
				.getCollection(GLOBAL_PARAMETERS);

		BasicDBObject query = new BasicDBObject();
		query.put("controllerId", id);
		DBCursor cursor = collection.find(query)
				.sort(new BasicDBObject("parameterDescId", -1)).limit(1);
		String value = null;
		while (cursor.iterator().hasNext()) {
			try {
				// LOGGER.info("pppppppppppp"+String.valueOf(cursor.next().get("parameterDescId")));
				value = String.valueOf(cursor.next().get("parameterDescId"));
				break;
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("Parameter Desc id not found for controller id :::"
						+ id);
			}
		}

		return value;

	}

	public Parameters findByParametersIp(String id) {
		// TODO Auto-generated method stub
		return mongoTemplate.findOne(
				Query.query(Criteria.where("controllerId").is(id)),
				Parameters.class);
	}

}
