package com.auction.pro.user.dao;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.auction.pro.common.dao.AbstractDAOImpl;
import com.auction.pro.user.dao.base.RoleDao;
import com.auction.pro.user.model.Role;

@Repository
public class RoleDaoImpl extends AbstractDAOImpl<Role> implements RoleDao {
	@Autowired
	MongoTemplate mongoTemplate;

	public Role findPermissionsByRoleId(Serializable id) {
		// TODO Auto-generated method stub
		return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)),
				Role.class);
	}

	@Override
	public boolean isExists(Role entity) {
		// TODO Auto-generated method stub
		return false;
	}
}
