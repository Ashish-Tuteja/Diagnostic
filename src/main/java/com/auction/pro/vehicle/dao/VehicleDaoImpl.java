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
import com.auction.pro.vehicle.dao.base.VehicleDao;
import com.auction.pro.vehicle.filter.VehicleFilter;
import com.auction.pro.vehicle.model.Ecu;
import com.auction.pro.vehicle.model.EcuController_backup;
import com.auction.pro.vehicle.model.EcuControllers;
import com.auction.pro.vehicle.model.GlobalParameter;
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
	
	
	/*
	 * public Vehicle findDefaultVehicle() {
	 * 
	 * 
	 * List<Vehicle> dVehicle = mongoTemplate.find(
	 * Query.query(Criteria.where("type").is(0)), Vehicle.class);
	 * System.out.println("Mongotemplate dvehicle"+dVehicle); return dVehicle !=
	 * null ? dVehicle.get(0) : null; { // TODO Auto-generated method stub
	 * Criteria and = new Criteria().andOperator(
	 * Criteria.where("type").is(searchFilter.getType())); return
	 * mongoTemplate.findOne(Query.query(and), Vehicle.class);
	 * 
	 * }
	 * 
	 * 
	 * }
	 */

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
	
	
	//getting datalist on the basis of vehicleControllerID
	public List<GlobalParameters> getDataListParameters(String vehicleControllerId) {

		DBCollection collection = mongoTemplate
				.getCollection(GLOBAL_PARAMETERS);

		List<GlobalParameters> globalParameters = new ArrayList<GlobalParameters>();
		List<BasicDBObject> andObjs = new ArrayList<BasicDBObject>();
		andObjs.add(new BasicDBObject().append("wasError",
				new BasicDBObject().append("$ne", "")));
		andObjs.add(new BasicDBObject().append("vehicleControllerId", vehicleControllerId));
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
	
	//return gblist on basis of vehicleControllerID
	

	public List<GlobalParameters> getDataList(String controllerId) {

		DBCollection collection = mongoTemplate
				.getCollection(GLOBAL_PARAMETERS);

		List<GlobalParameters> globalParameters = new ArrayList<GlobalParameters>();
		List<BasicDBObject> andObjs = new ArrayList<BasicDBObject>();
		/*andObjs.add(new BasicDBObject().append("isEnhanced",
				new BasicDBObject().append("$ne", "")));
		andObjs.add(new BasicDBObject().append("parameterId",
				new BasicDBObject().append("$ne", "")));
		andObjs.add(new BasicDBObject().append("bitpostion",
				new BasicDBObject().append("$ne", "")));
		andObjs.add(new BasicDBObject().append("serviceId",
				new BasicDBObject().append("$ne", "")));*/
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
	
	public List<EcuControllers> getEcuListParameters() {
		// TODO Auto-generated method stub
		List<EcuControllers> ecuControllers = new ArrayList<EcuControllers>();
		
			ecuControllers.addAll(mongoTemplate.findAll(EcuControllers.class, CONTROLLER_ECU));

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
	
	
	/*// Getting DataList parameters
	public List<GlobalParameters> getDataListParameters() {

		DBCollection collection = mongoTemplate
				.getCollection(GLOBAL_PARAMETERS);

		List<GlobalParameters> globalParameters = new ArrayList<GlobalParameters>();
		List<BasicDBObject> andObjs = new ArrayList<BasicDBObject>();
		andObjs.add(new BasicDBObject().append("controllerId",
				new BasicDBObject().append("$ne", "")));

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

	// End tags for dataList Parameters
*/
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

	

	public List<EcuController_backup> getECUController(List<String> controllerIds) {
		// TODO Auto-generated method stub
		List<EcuController_backup> ecuControllers = new ArrayList<EcuController_backup>();
		System.out.println("controller ids " + controllerIds);
		for (String controllerId : controllerIds) {
			ecuControllers.add(mongoTemplate.findOne(Query.query(Criteria
					.where("controllerId").is(controllerId)),
					EcuController_backup.class));

		}
		return ecuControllers;
	}

	// Updating existing parameter_tests for the same vehicle.

	
	
	public void updateGlobalParameters(List<GlobalParameters> parameters)
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

				and.add(new BasicDBObject().append("parameterDescId",
						globalParameters.getParameterDescId()));

				findObject.append("$and", and);
				setObject = (DBObject) JSON.parse(CommonUtils
						.convertPojoToJSon(globalParameters).toString());
				count++;
				collection.update(findObject, new BasicDBObject().append("$set", setObject), true, false);

			}
		}

		System.out.println("No of  Fields Updated in MongoDb where WasError = FALSE :: "
				+ count);
		
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

	// Adding new parameter_tests for a differenet vehicle.

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
				System.out.println(globalParameters.getParameterDescId());
				findObject.append("$and", and);

				setObject = (DBObject) JSON.parse(CommonUtils
						.convertPojoToJSon(globalParameters).toString());
				setObject.put("_id", CommonUtils.generateUUID());
				collection.save(setObject);
			}
		}

		
		System.out.println("No of  Fields saved in MongoDb where WasError = FALSE: " + count);

	}

	
	
		
	
	public boolean setGlobalECU(List<EcuController_backup> ecus) throws Exception {
		// TODO Auto-generated method stub
		if (ecus.size() == 0) {
			return false;
		}
		for (EcuController_backup globalECU : ecus) {
			EcuController_backup findObj = mongoTemplate.findOne(
					Query.query(Criteria.where("controllerId").is(
							globalECU.getControllerId())), EcuController_backup.class);
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
	
	// Saving Controller fields
			public void setControllerEcuParameters(EcuControllers controllerParameters)
					throws Exception {
				DBCollection collection = mongoTemplate.getCollection(CONTROLLER_ECU);
				DBObject setObject = new BasicDBObject();

				setObject = (DBObject) JSON.parse(CommonUtils.convertPojoToJSon(
						controllerParameters).toString());
				setObject.put("_id", CommonUtils.generateUUID());
				collection.save(setObject);
				

			}
	/*
	 * public void setGlobalParameters(List<GlobalParameters> parameters) throws
	 * Exception { // TODO Auto-generated method stub
	 * 
	 * DBCollection collection = mongoTemplate.getCollection(GLOBAL_PARAMETERS);
	 * BasicDBObject findObject = new BasicDBObject(); DBObject setObject = new
	 * BasicDBObject(); for (GlobalParameters globalParameter : parameters) {
	 * globalParameter
	 * .setParameterDescId(findParameterDescIdByControllerId(globalParameter
	 * .getControllerId())); BasicDBList and = new BasicDBList(); and.add(new
	 * BasicDBObject().append("controllerId",
	 * globalParameter.getControllerId())); and.add(new
	 * BasicDBObject().append("supportedbyECU",
	 * globalParameter.getSupportedByECU()));
	 * 
	 * findObject.append("$and", and); setObject = (DBObject)
	 * JSON.parse(CommonUtils.convertPojoToJSon( globalParameter).toString());
	 * collection.update(findObject, new BasicDBObject("$set", setObject), true,
	 * false); collection.update
	 * 
	 * 
	 * System.out.println("Saving in mongodb"); }
	 * 
	 * 
	 * 
	 * }
	 */
	// End braces for setting global parameters

	public EcuControllers getvehicleECU(String make , String model , String year) {
		// TODO Auto-generated method stub
		Criteria and = new Criteria().andOperator(
				Criteria.where("make").is(make), Criteria
						.where("model").is(model), Criteria
						.where("year").is(year));
		return mongoTemplate.findOne(Query.query(and), EcuControllers.class);

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