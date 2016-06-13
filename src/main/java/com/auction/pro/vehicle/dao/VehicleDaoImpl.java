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

import com.auction.pro.common.constants.MongoConstant;
import com.auction.pro.common.constants.NavResearchConstants;
import com.auction.pro.common.dao.AbstractDAOImpl;
import com.auction.pro.common.utils.CommonUtils;
import com.auction.pro.ecuController.model.EcuController;
import com.auction.pro.vehicle.dao.base.VehicleDao;
import com.auction.pro.vehicle.model.GlobalParameters;
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
		VehicleDao, MongoConstant, NavResearchConstants {
	@Autowired
	MongoTemplate mongoTemplate;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(VehicleDaoImpl.class.getName());

	public List<Vehicle> findBySerachterm(String searchterm,
			Serializable parentAccountId) throws Exception {

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
	
	//getting ecugbList on the basis of controllerids
		public List<GlobalParameters> getECUController(String[] controllerIds) {

			DBCollection collection = mongoTemplate
					.getCollection(GLOBAL_PARAMETERS);

			List<GlobalParameters> globalParameters = new ArrayList<GlobalParameters>();
			
			for (String controllerId : controllerIds) {
				List<BasicDBObject> andObjs = new ArrayList<BasicDBObject>();
			andObjs.add(new BasicDBObject().append("controllerId", controllerId));
			BasicDBObject findOBJ = new BasicDBObject();
			findOBJ.append("$and", andObjs);
				DBCursor cursor = collection.find(findOBJ);
				
			while (cursor.hasNext()) {
				try {
					globalParameters.add(GlobalParameters
							.convertGlobalParameters(new JSONObject(
									((BasicDBObject) cursor.next()).toString())));

				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error("convert json to pojo error");
				}
			}
			
			
			}
			return globalParameters;
		}
		
		//return gblist on basis of vehicleControllerID

	public List<GlobalParameters> getDataList(String controllerId) {

		DBCollection collection = mongoTemplate
				.getCollection(GLOBAL_PARAMETERS);

		List<GlobalParameters> globalParameters = new ArrayList<GlobalParameters>();
		List<BasicDBObject> andObjs = new ArrayList<BasicDBObject>();
		andObjs.add(new BasicDBObject().append("wasError",
				new BasicDBObject().append("$ne", "")));
		andObjs.add(new BasicDBObject().append("controllerId", controllerId));
		BasicDBObject findOBJ = new BasicDBObject();
		findOBJ.append("$and", andObjs);

		DBCursor cursor = collection.find(findOBJ);

		while (cursor.hasNext()) {
			try {
				globalParameters.add(GlobalParameters
						.convertGlobalParameters(new JSONObject(
								((BasicDBObject) cursor.next()).toString())));

			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("convert json to pojo error");
			}

		}
		return globalParameters;
	}

	
	//Getting ecuList parameters
	
	public List<EcuController> getEcuListParameters() {
		// TODO Auto-generated method stub
		List<EcuController> ecuControllers = new ArrayList<EcuController>();
		
			ecuControllers.addAll(mongoTemplate.findAll(EcuController.class, CONTROLLER_ECU));

		return ecuControllers;
	}

	//end tags for ecuList parameters
	
	//Getting dataList parameters
	
		public List<GlobalParameters> getDataListParameters() {
			// TODO Auto-generated method stub
			List<GlobalParameters> globalParameters = new ArrayList<GlobalParameters>();
			
			globalParameters.addAll(mongoTemplate.findAll(GlobalParameters.class, GLOBAL_PARAMETERS));

			return globalParameters;
		}

		//end tags for ecuList parameters
	
	

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

	
	public List<EcuController> getECUControllers(List<String> controllerIds) {
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
	

	
	
	// Updating existing parameter_tests for the same vehicle.
	public void deleteGlobalParameters(List<String> uploadList)
			throws Exception {
		// TODO Auto-generated method stub
		DBCollection collection = mongoTemplate
				.getCollection(GLOBAL_PARAMETERS);
		int count = 0;
		for (String id: uploadList) {
			count++;
			collection.remove(new BasicDBObject().append("controllerId", id));
		}

		System.out.println("No of  Fields Deleted in MongoDb where WasError = FALSE :: "
				+ count);
		
	}
	

	// Adding new parameter_tests for a different vehicle.
	public void setGlobalParameters(List<GlobalParameters> parameters)
			throws Exception {
		// TODO Auto-generated method stub
		DBCollection collection = mongoTemplate
				.getCollection(GLOBAL_PARAMETERS);
		BasicDBObject findObject = new BasicDBObject();
		DBObject setObject = new BasicDBObject();
		int count = 0;
		for (GlobalParameters globalParameters : parameters) {
			
			BasicDBList and = new BasicDBList();
			if (globalParameters.getWasError().length() == 5
					&& globalParameters.getWasError() != null) {

				and.add(new BasicDBObject().append("wasError",
						globalParameters.getWasError()));
				count++;
				findObject.append("$and", and);

				setObject = (DBObject) JSON.parse(CommonUtils
						.convertPojoToJSon(globalParameters).toString());
				setObject.put("_id", CommonUtils.generateUUID());
				collection.save(setObject);
			}
		}

		
		System.out.println("No of  Fields saved in MongoDb where WasError = FALSE: " + count);

	}

	
	
		
	
	// Saving Controller fields
			public void setControllerEcuParameters(EcuController controllerParameters)
					throws Exception {
				DBCollection collection = mongoTemplate.getCollection(CONTROLLER_ECU);
				DBObject setObject = new BasicDBObject();

				setObject = (DBObject) JSON.parse(CommonUtils.convertPojoToJSon(
						controllerParameters).toString());
				setObject.put("_id", CommonUtils.generateUUID());
				collection.save(setObject);
				

			}

	public EcuController getvehicleECU(String make , String model , String year) {
		// TODO Auto-generated method stub
		Criteria and = new Criteria().andOperator(
				Criteria.where("make").is(make), Criteria
						.where("model").is(model), Criteria
						.where("year").is(year));
		return mongoTemplate.findOne(Query.query(and), EcuController.class);

	}

}