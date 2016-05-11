package com.auction.pro.account.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.auction.pro.account.dao.base.AccountDao;
import com.auction.pro.account.model.Account;
import com.auction.pro.common.constants.MongoConstant;
import com.auction.pro.common.dao.AbstractDAOImpl;
import com.auction.pro.user.model.AccountUserMap;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

@Repository
public class AccountDaoImpl extends AbstractDAOImpl<Account> implements
		AccountDao, MongoConstant {
	@Autowired
	MongoTemplate mongoTemplate;
	private DBCollection collection;

	@SuppressWarnings("unchecked")
	public <T> T findAccountIdByUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		BasicDBObject query = new BasicDBObject("userId", userId);
		DBCursor cursor = mongoTemplate.getCollection("accountUserMap").find(
				query);
		BasicDBObject doc = (BasicDBObject) (cursor.hasNext() ? cursor.next()
				: new BasicDBObject());
		return (T) doc.get("accountId");
	}

	public Account findByActivationId(String activationId) throws Exception {
		// TODO Auto-generated method stub
		return mongoTemplate.findOne(
				Query.query(Criteria.where("activationId").is(activationId)),
				Account.class);
	}

	public void activation(String activationId) throws Exception {
		// TODO Auto-generated method stub
		collection = mongoTemplate.getCollection(ACCOUNT);
		BasicDBObject activateAccount = new BasicDBObject();
		activateAccount.append("$set",
				new BasicDBObject().append("status", true));
		BasicDBObject searchQuery = new BasicDBObject().append("activationId",
				activationId);
		collection.update(searchQuery, activateAccount);
	}

	public Account findByAccountName(Account accountDto) throws Exception {
		// TODO Auto-generated method stub
		Criteria criteria = new Criteria("accountName").regex(accountDto
				.getAccountName());
		return mongoTemplate.findOne(Query.query(criteria), Account.class);
	}

	public Account findByUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		Criteria criteria = new Criteria("_id").regex(mongoTemplate.findOne(
				new Query(Criteria.where("userId").is(userId)),
				AccountUserMap.class).getAccountId());
		return mongoTemplate.findOne(Query.query(criteria), Account.class);
	}

	public List<Account> findByParentAccountId(String parentId,
			List<String> fields) throws Exception {
		// TODO Auto-generated method stub
		Criteria criteria = new Criteria().orOperator(
				Criteria.where("parentAccountId").all(parentId), Criteria
						.where("_id").is(parentId));
		Query query = new Query(criteria);
		addFields(fields, query);
		return mongoTemplate.find(query, Account.class);
	}

	public List<Account> findBySerachterm(String searchterm,
			Serializable parentAccountId) throws Exception {
		// TODO Auto-generated method stub
		return mongoTemplate.find(
				new Query(new Criteria().andOperator(new Criteria().orOperator(
						Criteria.where("parentAccountId").all(parentAccountId),
						Criteria.where("_id").is(parentAccountId)), Criteria
						.where("accountName").regex(searchterm))),
				Account.class);
	}

	public Account findByContactId(Serializable contactId) throws Exception {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("contactId").is(contactId));
		return mongoTemplate.findOne(query, Account.class);
	}

	@Override
	public boolean isExists(Account entity) {
		// TODO Auto-generated method stub
		return mongoTemplate.findOne(
				Query.query(Criteria.where("accountName").is(
						entity.getAccountName())), Account.class) != null ? true
				: false;
	}

	public Page<Account> findAllPage(Pageable pageable, String parentAccountId)
			throws Exception {
		// TODO Auto-generated method stub
		List<Account> list = null;
		Query query = new Query();
		query.addCriteria(new Criteria().orOperator(
				Criteria.where("parentAccountId").all(parentAccountId),
				Criteria.where("_id").is(parentAccountId)));
		query.with(pageable);
		list = mongoTemplate.find(query, Account.class);
		Page<Account> entityPage = new PageImpl<Account>(list, pageable,
				findByParentAccountId(parentAccountId, null).size());
		return entityPage;
	}
}
