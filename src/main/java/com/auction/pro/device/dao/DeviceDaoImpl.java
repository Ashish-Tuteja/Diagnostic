package com.auction.pro.device.dao;

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
import com.auction.pro.device.dao.base.DeviceDao;
import com.auction.pro.device.model.Carrier;
import com.auction.pro.device.model.Device;
import com.auction.pro.device.model.DeviceType;

@Repository
public class DeviceDaoImpl extends AbstractDAOImpl<Device> implements
		DeviceDao, MongoConstant {
	@Autowired
	MongoTemplate mongoTemplate;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DeviceDaoImpl.class.getName());

	public List<Device> findBySerachterm(String searchterm,
			Serializable parentAccountId) throws Exception {
		// TODO Auto-generated method stub
		return mongoTemplate.find(new Query(new Criteria().andOperator(
		/* Criteria.where("parentAccountId").all(parentAccountId), */
		Criteria.where("deviceName").regex(searchterm))), Device.class);

	}

	public List<Carrier> getCarriers() throws Exception {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(Carrier.class);
	}

	public List<DeviceType> getDeviceTypes() throws Exception {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(DeviceType.class);
	}

	public List<Device> getDevices() throws Exception {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(Device.class);
	}

	@Override
	public boolean isExists(Device entity) {
		// TODO Auto-generated method stub
		try {
			return (mongoTemplate.findOne(
					new Query(new Criteria().orOperator(Criteria.where("ip")
							.is(entity.getIp()))), Device.class).getId() != null) ? true
					: false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public Page<Device> findAllPage(Pageable pageable, String parentAccountId)
			throws Exception {
		// TODO Auto-generated method stub
		List<Device> list = null;
		Query query = new Query();
		/*
		 * query.addCriteria(Criteria.where("parentAccountId")
		 * .all(parentAccountId));
		 */
		query.with(pageable);
		list = mongoTemplate.find(query, Device.class);
		Page<Device> entityPage = new PageImpl<Device>(list, pageable,
				mongoTemplate.count(
				/*
				 * Query.query(Criteria.where("parentAccountId").all(
				 * parentAccountId))
				 */new Query(), Device.class));
		return entityPage;
	}

	public Device findByDeviceIp(String ip) {
		// TODO Auto-generated method stub
		return mongoTemplate.findOne(Query.query(Criteria.where("ip").is(ip)),
				Device.class);
	}

}
