package com.auction.pro.user.dao;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.auction.pro.account.model.Account;
import com.auction.pro.common.constants.MongoConstant;
import com.auction.pro.common.dao.AbstractDAOImpl;
import com.auction.pro.user.dao.base.UserDao;
import com.auction.pro.user.model.Contact;
import com.auction.pro.user.model.User;
import com.google.common.collect.ImmutableSet;
import com.mongodb.CommandResult;

@Repository
public class UserDaoImpl extends AbstractDAOImpl<User> implements UserDao,
		MongoConstant {
	@Autowired
	MongoTemplate mongoTemplate;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserDaoImpl.class.getName());

	public List<User> findBySerachterm(String searchterm, List<String> userIds)
			throws Exception {
		List<Criteria> criterias = new ArrayList<Criteria>();
		for (String userId : userIds) {
			criterias.add(new Criteria("_id").regex(userId));
		}
		return mongoTemplate.find(Query.query(new Criteria().andOperator(
				new Criteria().orOperator(criterias
						.toArray(new Criteria[criterias.size()])),
				new Criteria().orOperator(
						Criteria.where("username").regex(searchterm), Criteria
								.where("emailId").regex(searchterm), Criteria
								.where("firstName").regex(searchterm), Criteria
								.where("lastName").regex(searchterm)))),
				User.class);
	}

	@SuppressWarnings("unchecked")
	public List<Account> findByAccountId(String id) throws Exception {
		return mongoTemplate.find(
				Query.query(Criteria.where("accountId").is(id)), Account.class);
	}

	public Contact findContactById(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("_id").is(id));
		return mongoTemplate.findOne(query, Contact.class);
	}

	public User setPermissions(User user) throws Exception {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("_id").is(user.getId()));
		Update update = new Update().set("userPermissions",
				user.getUserPermissions());
		CommandResult commandResult = mongoTemplate.updateFirst(query, update,
				User.class).getLastError();
		if (commandResult.getErrorMessage() == ""
				|| commandResult.getErrorMessage() == "") {
			return user;
		}
		return null;
	}

	public boolean setPassword(User user) throws Exception {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("_id").is(user.getId()));
		Update update = new Update().set("password", user.getPassword()).set(
				"cnfpassword", user.getPassword());
		CommandResult commandResult = mongoTemplate.updateFirst(query, update,
				User.class).getLastError();
		if (commandResult.getErrorMessage() == ""
				|| commandResult.getErrorMessage() == "") {
			return true;
		}
		return false;
	}

	@Override
	public boolean isExists(User entity) {
		// TODO Auto-generated method stub
		try {
			return mongoTemplate.findOne(
					new Query(
							new Criteria().orOperator(
									Criteria.where("username").is(
											entity.getUserName()),
									Criteria.where("emailId").is(
											entity.getEmailid()))), User.class) == null ? false
					: true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	public Page<User> findAllPage(Pageable pageable, List<String> userIds) {
		// TODO Auto-generated method stub
		List<Criteria> criterias = new ArrayList<Criteria>();
		for (String userId : userIds) {
			criterias.add(new Criteria("_id").regex(userId));
		}
		Criteria criteria = new Criteria().orOperator(criterias
				.toArray(new Criteria[criterias.size()]));
		List<User> list = null;
		Query query = new Query();
		query.addCriteria(criteria);
		query.with(pageable);
		list = mongoTemplate.find(query, User.class);
		Page<User> entityPage = new PageImpl<User>(list, pageable, ImmutableSet
				.copyOf(userIds).asList().size());
		return entityPage;
	}

}
