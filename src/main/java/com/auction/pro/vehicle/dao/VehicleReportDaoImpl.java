package com.auction.pro.vehicle.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
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
import com.auction.pro.vehicle.dao.base.VehicleReportDao;
import com.auction.pro.vehicle.model.CanParameters;
import com.auction.pro.vehicle.model.VehicleReport;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Repository
public class VehicleReportDaoImpl extends AbstractDAOImpl<VehicleReport>
implements VehicleReportDao, MongoConstant {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(VehicleReportDaoImpl.class.getName());
	@Autowired
	MongoTemplate mongoTemplate;


	@Override
	public boolean isExists(VehicleReport entity) {
		// TODO Auto-generated method stub
		return false;
	}

	public Map<String, Object> findbyGroupId(String groupid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> out = new LinkedHashMap<String, Object>();
		try {
			String make = null;
			List<Map<String, Object>> listReport = new ArrayList<Map<String, Object>>();
			Map<String,String[]> details = new HashMap<String,String[]>();
			Map<String,String[]> canDetails = new HashMap<String,String[]>();
			Query query = new Query();
			DBCollection vehicleColl = mongoTemplate.getCollection(VEHICLE);
			query.addCriteria(Criteria.where("reportgroupId").is(groupid));
			List<VehicleReport> vehicleReports = mongoTemplate.find(query,
					VehicleReport.class);
			Collections.sort(vehicleReports);
			String[] timestamp = new String[vehicleReports.size()];
			Integer[] packetType = new Integer[vehicleReports.size()];
			int i = 0;
			for (VehicleReport vehicleReport : vehicleReports) {
				listReport.add(vehicleReport.getReport());
				packetType[i] = vehicleReport.getPacketType();
				timestamp[i++] = vehicleReport.getReportType() + ","
						+ vehicleReport.getTimeStamp();
			}

			Query cQuery = new Query();
			List<CanParameters> canReports = mongoTemplate.find(cQuery,
					CanParameters.class);

			for(CanParameters canReport: canReports){
				String canArray[] =  {
					canReport.getMessageId(),
					canReport.getDescription()
				};
				canDetails.put(canReport.getFunctionId(), canArray);
			}

			DBCursor cur = vehicleColl.find(new BasicDBObject("reportgroupIds", groupid));
			if(cur.hasNext()) {
				for (DBObject dbObject : cur) {
					BasicDBObject whereFields = new BasicDBObject();
					whereFields.put("make", dbObject.get("make"));
					whereFields.put("model", dbObject.get("model"));
					whereFields.put("year", dbObject.get("year"));

					// fetch the vehicle make for handling odometer
					make = dbObject.get("make").toString();

					DBCollection ecuControllerColl = mongoTemplate.getCollection(CONTROLLER_ECU);
					DBCursor cur1 = ecuControllerColl.find(whereFields);

					if(cur1.hasNext()) {
						for (DBObject dbObject2 : cur1) {

							DBCollection parameterTestColl = mongoTemplate.getCollection(GLOBAL_PARAMETERS);
							BasicDBObject condition = new BasicDBObject();
							condition.append("controllerId", dbObject2.get("controllerId"));
							condition.append("wasError", "FALSE");
							DBCursor cur2 = parameterTestColl.find(condition);

							if(cur2.hasNext()) {
								for (DBObject dbObject3 : cur2) {
									if ( listReport.size() != 0 ) {
										for (Map a : listReport) {
											Iterator it = a.entrySet().iterator();
											while (it.hasNext()) {
												Map.Entry pair = (Map.Entry)it.next();
												Object reportObj = pair.getValue();
												String splitArr[] = reportObj.toString().split(";");

												for (String s : splitArr) {
													String parameterDescIdByTestString = s.split(":")[0];

													if(parameterDescIdByTestString.equalsIgnoreCase(
															dbObject3.get("parameterDescId").toString())) {

														String arr[] = {
															dbObject3.get("parameterDesc").toString(),
															dbObject3.get("parameterId").toString(),
															String.valueOf(dbObject3.get("formula")),
															String.valueOf(dbObject3.get("units").toString()),
															String.valueOf(dbObject3.get("valueOOx")),
															String.valueOf(dbObject3.get("valueOFF"))
														};
														details.put(parameterDescIdByTestString, arr);
													} // end if
												} // end report split loop
											}
										} // end report loop
									} // end if
								}
							}
						}
					}
				}
			}

			//pid
			out.put("canDetails", canDetails);
			out.put("json", details);
			out.put("timestamp", timestamp);
			out.put("reports", listReport);
			out.put("packetType", packetType);
			out.put("make", make);

		} catch(Exception e) {
			LOGGER.error("No values found for map");
			e.printStackTrace();
		}

		//find pid , paramdesc
		return out;
	}

	public Page<VehicleReport> findAllPage(Pageable pageable,
			String parentAccountId) throws Exception {
		// TODO Auto-generated method stub
		List<VehicleReport> list = null;
		Query query = new Query();
		query.with(pageable);
		list = mongoTemplate.find(query, VehicleReport.class);
		Page<VehicleReport> entityPage = new PageImpl<VehicleReport>(list,
				pageable, mongoTemplate.count(new Query(), VehicleReport.class));
		return entityPage;
	}

	public List<VehicleReport> findReports(String ip, Set<String> reportgroupId)
			throws Exception {
		// TODO Auto-generated method stub
		List<VehicleReport> reports = new ArrayList<VehicleReport>();
		Map<String, Integer> groupIds = new HashMap<String, Integer>();
		DBCollection collection = mongoTemplate.getCollection(VEHICLE_REPORT);
		BasicDBObject query = new BasicDBObject();
		BasicDBObject keys = new BasicDBObject();
		BasicDBList groupIdCheck = new BasicDBList();
		if (!StringUtils.isNotBlank(ip)) {
			for (String reportGID : reportgroupId) {
				groupIdCheck.add(new BasicDBObject().append("reportgroupId",
						reportGID));

			}
			query.append("$or", groupIdCheck);
		} else {
			query.append("ip", ip);
		}

		keys.put("timeStamp", 1);
		keys.put("reportgroupId", 1);
		LOGGER.info("Query is -> " + query);
		DBCursor cursor = collection.find(query, keys).sort(
				new BasicDBObject().append("timeStamp", -1));
		while (cursor.hasNext()) {
			BasicDBObject obj = (BasicDBObject) cursor.next();
			VehicleReport report = new VehicleReport();
			report.setTimeStamp(obj.getLong("timeStamp"));
			report.setReportgroupId(obj.getString("reportgroupId"));
			Integer count = get(report.getReportgroupId(), groupIds);
			if (count == null) {
				groupIds.put(report.getReportgroupId(), 1);
				reports.add(report);
			} else {
				groupIds.put(report.getReportgroupId(), count + 1);
			}

		}
		LOGGER.info("fetching reports");
		for (VehicleReport report : reports) {
			report.setReportCount(groupIds.get(report.getReportgroupId()));
		}
		return reports;
	}

	private Integer get(Object key, Map<String, Integer> groupId) {
		Integer m = groupId.get(key);
		if (m != null) {
			return m;
		}
		return null;
	}

}
