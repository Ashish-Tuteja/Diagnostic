package com.auction.pro.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.auction.pro.common.constants.MongoConstant;
import com.auction.pro.common.dao.AbstractDAOImpl;
import com.auction.pro.common.utils.CommonUtils;
import com.auction.pro.user.dao.base.UserLoginDao;
import com.auction.pro.user.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

@Repository
public class UserLoginDaoImpl extends AbstractDAOImpl<User> implements
		UserLoginDao, MongoConstant {
	private String password;
	@Autowired
	private MongoTemplate mongoTemplate;
	private DBCollection collection;

	@SuppressWarnings("unchecked")
	public User findByUsername(User user) throws JSONException {
		collection = mongoTemplate.getCollection(USER);
		BasicDBObject query1 = new BasicDBObject("emailid", user.getEmailid());
		BasicDBObject query2 = new BasicDBObject("username", user.getUserName());
		ArrayList<BasicDBObject> myList = new ArrayList<BasicDBObject>();
		myList.add(query1);
		myList.add(query2);
		BasicDBObject whereQuery = new BasicDBObject("$or", myList);
		BasicDBObject fields = new BasicDBObject();
		fields.put("password", 1);
		fields.put("_id", 1);
		fields.put("roleId", 1);
		fields.put("userPermissions", 1);
		fields.put("emailid", 1);
		fields.put("username", 1);
		DBCursor cursor = collection.find(whereQuery, fields);
		BasicDBObject doc = (BasicDBObject) (cursor.hasNext() ? cursor.next()
				: new BasicDBObject());
		Object obj = doc.get("password");
		Object objId = doc.get("_id");
		if (checkAccountIsActive(objId.toString())) {
			password = obj == null ? "null" : obj.toString();
			user.setPassword(password);
			user.setRoleId(doc.getString("roleId"));
			user.setId(String.valueOf(objId));
			user.setUserPermissions((List<String>) doc.get("userPermissions"));
			user.setEmailid(doc.getString("emailid"));
			user.setId(doc.getString("_id"));
		}
		return user;
	}

	public int enableLoginAttempts(String user) {
		// TODO Auto-generated method stub
		collection = mongoTemplate.getCollection(USER);
		BasicDBObject searchUser = new BasicDBObject();
		BasicDBObject updateAttempts = new BasicDBObject();
		if (CommonUtils.checkIsUsernameOrEmailId(user)) {
			searchUser.append("emailid", user);
		} else {
			searchUser.append("username", user);
		}
		int loginAttempts = getLoginAttempts(user);
		updateAttempts.append("$set",
				new BasicDBObject().append("loginAttempts", loginAttempts + 1));
		collection.update(searchUser, updateAttempts);
		return loginAttempts;
	}

	public int getLoginAttempts(String user) {
		// TODO Auto-generated method stub
		collection = mongoTemplate.getCollection(USER);
		BasicDBObject getAttempts = new BasicDBObject();
		if (CommonUtils.checkIsUsernameOrEmailId(user)) {
			getAttempts.put("emailid", user);
		} else {
			getAttempts.put("username", user);
		}
		DBCursor cursor = collection.find(getAttempts);
		BasicDBObject doc = (BasicDBObject) (cursor.hasNext() ? cursor.next()
				: new BasicDBObject());
		try {
			int attempts = ((Double.parseDouble(String.valueOf(doc
					.get("loginAttempts")))) == 0.0)
					|| ((Double.parseDouble(String.valueOf(doc
							.get("loginAttempts")))) == 0) ? 0 : Integer
					.parseInt(String.valueOf(doc.get("loginAttempts")));
			return attempts;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}

	}

	public void disableLoginAttempts(String user) {
		// TODO Auto-generated method stub
		collection = mongoTemplate.getCollection(USER);
		BasicDBObject searchUser = new BasicDBObject();
		BasicDBObject disableAttempts = new BasicDBObject();
		if (CommonUtils.checkIsUsernameOrEmailId(user)) {

			searchUser.append("emailid", user);
		} else {

			searchUser.append("username", user);
		}
		disableAttempts.append("$set",
				new BasicDBObject().append("loginAttempts", 0));
		collection.update(searchUser, disableAttempts);
	}

	public boolean checkAccountIsActive(String userId) {
		try {
			BasicDBObject accountUserMap = new BasicDBObject("userId", userId);
			DBCursor cursor = mongoTemplate.getCollection(ACCOUNT_USER_MAP)
					.find(accountUserMap);
			BasicDBObject accountUserMapDoc = (BasicDBObject) (cursor.hasNext() ? cursor
					.next() : new BasicDBObject());
			BasicDBObject checkAccountStatus = new BasicDBObject("_id",
					accountUserMapDoc.get("accountId"));
			DBCursor accountStatusCursor = mongoTemplate.getCollection(ACCOUNT)
					.find(checkAccountStatus);
			BasicDBObject accountStatusDoc = (BasicDBObject) (accountStatusCursor
					.hasNext() ? accountStatusCursor.next()
					: new BasicDBObject());
			return Boolean.parseBoolean(String.valueOf(accountStatusDoc
					.get("status")));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean isExists(User entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
