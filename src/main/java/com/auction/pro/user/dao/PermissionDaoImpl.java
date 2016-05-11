package com.auction.pro.user.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.auction.pro.common.dao.AbstractDAOImpl;
import com.auction.pro.user.dao.base.PermissionDao;
import com.auction.pro.user.model.Permission;

@Repository
public class PermissionDaoImpl extends AbstractDAOImpl<Permission> implements
		PermissionDao {
	@Autowired
	MongoTemplate mongoTemplate;

	public List<Permission> findPermissions() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(Permission.class);
	}

	@Override
	public boolean isExists(Permission entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
