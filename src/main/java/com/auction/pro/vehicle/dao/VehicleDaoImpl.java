package com.auction.pro.vehicle.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.auction.pro.common.constants.CimbleConstants;
import com.auction.pro.common.constants.MongoConstant;
import com.auction.pro.common.dao.AbstractDAOImpl;
import com.auction.pro.common.utils.CommonUtils;
import com.auction.pro.vehicle.dao.base.VehicleDao;
import com.auction.pro.vehicle.filter.VehicleFilter;
import com.auction.pro.vehicle.model.Ecu;
import com.auction.pro.vehicle.model.EcuController;
import com.auction.pro.vehicle.model.GlobalParameter;
import com.auction.pro.vehicle.model.Vehicle;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
import com.mongodb.util.JSON;

@Repository
public class VehicleDaoImpl extends AbstractDAOImpl<Vehicle> implements
		VehicleDao, MongoConstant, CimbleConstants {
	@Autowired
	MongoTemplate mongoTemplate;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(VehicleDaoImpl.class.getName());

	public List<Vehicle> findBySerachterm(String searchterm,
			Serializable parentAccountId) throws Exception {
		// TODO Auto-generated method stub

		return mongoTemplate.find(new Query(new Criteria().andOperator(
		/* Criteria.where("parentAccountId").all(parentAccountId), */
		Criteria.where("vin").regex(searchterm))), Vehicle.class);

	}

	@Override
	public boolean isExists(Vehicle entity) {
		// TODO Auto-generated method stub
		return false;
	}

	public Page<Vehicle> findAllPage(Pageable pageable, String parentAccountId)
			throws Exception {
		// TODO Auto-generated method stub
		List<Vehicle> list = null;
		Query query = new Query();
		/*
		 * query.addCriteria(Criteria.where("parentAccountId")
		 * .all(parentAccountId));
		 */
		query.with(pageable);
		list = mongoTemplate.find(query, Vehicle.class);

		Page<Vehicle> entityPage = new PageImpl<Vehicle>(list, pageable,
				mongoTemplate.count(
				/*
				 * Query.query(Criteria.where("parentAccountId").all(
				 * parentAccountId))
				 */new Query(), Vehicle.class));
		return entityPage;

	}

	public Vehicle findByVIN(String vin) throws Exception {
		// TODO Auto-generated method stub

		return vin != null ? mongoTemplate.findOne(
				Query.query(Criteria.where("vin").is(vin)), Vehicle.class)
				: null;
	}

	public int updateTimeStamp(String vin) throws Exception {
		// TODO Auto-generated method stub
		Update update = new Update();
		update.push("timestamp", Calendar.getInstance().getTimeInMillis());
		return mongoTemplate.updateFirst(
				Query.query(Criteria.where("vin").is(vin)), update,
				Vehicle.class).getN();
	}

	public void setVehicleReportCount(String vin) throws Exception {
		// TODO Auto-generated method stub
		DBCollection collection = mongoTemplate.getCollection(VEHICLE);
		BasicDBObject query = new BasicDBObject();
		query.put("vin", vin);
		BasicDBObject incValue = new BasicDBObject("vreportcounter", 1);
		BasicDBObject intModifier = new BasicDBObject("$inc", incValue);
		collection.update(query, intModifier, false, false, WriteConcern.SAFE);
	}

	public void insertVehicleReportGroupId(String groupId, String vin)
			throws Exception {
		// TODO Auto-generated method stub
		DBCollection collection = mongoTemplate.getCollection(VEHICLE);
		BasicDBObject findObject = new BasicDBObject();
		findObject.append("reportgroupIds",
				new BasicDBObject().append("$in", Arrays.asList(groupId)));
		if (collection.find(findObject).size() < 1) {
			Update updateObj = new Update();
			updateObj.push("reportgroupIds", groupId);
			mongoTemplate.updateFirst(
					Query.query(Criteria.where("vin").is(vin)), updateObj,
					Vehicle.class);
			LOGGER.info("Insert groupId");
		} else {
			LOGGER.info("GroupId already exists");
		}

	}

	public List<GlobalParameter> getDataList(String controllerId) {

		DBCollection collection = mongoTemplate.getCollection(GLOBAL_PARAMRTER);

		List<GlobalParameter> globalParameters = new ArrayList<GlobalParameter>();
		List<BasicDBObject> andObjs = new ArrayList<BasicDBObject>();
		andObjs.add(new BasicDBObject().append("isEnhanced",
				new BasicDBObject().append("$ne", "")));
		andObjs.add(new BasicDBObject().append("parameterId",
				new BasicDBObject().append("$ne", "")));
		andObjs.add(new BasicDBObject().append("bitpostion",
				new BasicDBObject().append("$ne", "")));
		andObjs.add(new BasicDBObject().append("serviceId",
				new BasicDBObject().append("$ne", "")));
		andObjs.add(new BasicDBObject().append("bitwidth",
				new BasicDBObject().append("$ne", "")));
		andObjs.add(new BasicDBObject().append("supportedbyECU", controllerId));
		BasicDBObject findOBJ = new BasicDBObject();
		findOBJ.append("$and", andObjs);

		System.out.println("Search Global " + findOBJ.toString());

		DBCursor cursor = collection.find(findOBJ);

		while (cursor.hasNext()) {
			try {
				globalParameters.add(GlobalParameter
						.convertGlobalParameter(new JSONObject(
								((BasicDBObject) cursor.next()).toString())));

			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("convert json to pojo error");
			}

		}
		return globalParameters;
	}

	public boolean setVehicleECU(List<Ecu> ecus) throws Exception {
		if (ecus.size() == 0) {
			return false;
		}
		DBCollection collection = mongoTemplate.getCollection(VEHICLE_ECU);
		List<BasicDBObject> andObject = new ArrayList<BasicDBObject>();
		BasicDBObject findObject = new BasicDBObject();
		DBObject setObject = new BasicDBObject();
		for (Ecu vehicleECU : ecus) {
			andObject.add(new BasicDBObject().append("make",
					vehicleECU.getMake()));
			andObject.add(new BasicDBObject().append("model",
					vehicleECU.getModel()));
			findObject.append("$and", andObject);
			setObject = (DBObject) JSON.parse(CommonUtils.convertPojoToJSon(
					vehicleECU).toString());
			collection.update(findObject, new BasicDBObject("$set", setObject),
					true, false);

		}
		return true;

	}

	public List<String> getControllerIds(String make, String model,
			String startYear, String endYear) {
		// TODO Auto-generated method stub
		List<String> controllerIds = new LinkedList<String>();
		DBCollection collection = mongoTemplate.getCollection(VEHICLE_ECU);
		BasicDBList and = new BasicDBList();
		and.add(new BasicDBObject().append("vehicleMake", make));
		and.add(new BasicDBObject().append("vehicleModel", model));
		and.add(new BasicDBObject().append("vehicleStartYear", startYear));
		and.add(new BasicDBObject().append("vehicleEndYear", endYear));
		System.out.println("search controller ID "
				+ new BasicDBObject().append("$and", and).toString());
		DBCursor cursor = collection.find(new BasicDBObject().append("$and",
				and));
		while (cursor.hasNext()) {
			BasicDBObject object = (BasicDBObject) cursor.next();
			controllerIds.add(String.valueOf(object.get("engineController")));

		}

		return controllerIds;
	}

	public boolean setGlobalECU(List<EcuController> ecus) throws Exception {
		// TODO Auto-generated method stub
		if (ecus.size() == 0) {
			return false;
		}
		for (EcuController globalECU : ecus) {
			EcuController findObj = mongoTemplate.findOne(
					Query.query(Criteria.where("controllerId").is(
							globalECU.getControllerId())), EcuController.class);
			if (findObj == null) {
				if (globalECU.getId() == null
						|| globalECU.getId().length() <= 0) {
					globalECU.setId(CommonUtils.generateUUID());
				}

				mongoTemplate.save(globalECU);
			}
		}
		return true;

	}

	public List<EcuController> getECUController(List<String> controllerIds) {
		// TODO Auto-generated method stub
		List<EcuController> ecuControllers = new ArrayList<EcuController>();
		System.out.println("controller ids " + controllerIds);
		for (String controllerId : controllerIds) {
			ecuControllers.add(mongoTemplate.findOne(Query.query(Criteria
					.where("controllerId").is(controllerId)),
					EcuController.class));

		}
		return ecuControllers;
	}

	public void setGlobalparameter(List<GlobalParameter> parameters)
			throws Exception {
		// TODO Auto-generated method stub

		DBCollection collection = mongoTemplate.getCollection(GLOBAL_PARAMRTER);
		BasicDBObject findObject = new BasicDBObject();
		DBObject setObject = new BasicDBObject();
		for (GlobalParameter globalParameter : parameters) {
			globalParameter
					.setParameterDescId(findParameterDescIdByControllerId(globalParameter
							.getControllerId()));
			BasicDBList and = new BasicDBList();
			and.add(new BasicDBObject().append("controllerId",
					globalParameter.getControllerId()));
			and.add(new BasicDBObject().append("supportedbyECU",
					globalParameter.getSupportedbyECU()));

			findObject.append("$and", and);
			setObject = (DBObject) JSON.parse(CommonUtils.convertPojoToJSon(
					globalParameter).toString());
			collection.update(findObject, new BasicDBObject("$set", setObject),
					true, false);

		}

	}

	public Ecu getvehicleECU(VehicleFilter searchterm) {
		// TODO Auto-generated method stub
		Criteria and = new Criteria().andOperator(
				Criteria.where("make").is(searchterm.getMake()), Criteria
						.where("model").is(searchterm.getModel()), Criteria
						.where("year").is(searchterm.getYear()));
		return mongoTemplate.findOne(Query.query(and), Ecu.class);

	}

	/*
	 * This return parameter's description id from parameter_description
	 * document by controller ID of parameter_test
	 */
	private int findParameterDescIdByControllerId(String controllerId) {
		DBCollection collection = mongoTemplate
				.getCollection(PARAMETER_DESCRIPTION);
		BasicDBObject findobj = new BasicDBObject();
		findobj.append("description", controllerId);
		DBObject dbObject = collection.findOne(findobj);
		if (dbObject == null) {
			return 0;
		}
		System.out.println(dbObject.get("parameterId"));
		return Integer.parseInt(String.valueOf(dbObject.get("parameterId")));
	}

}
