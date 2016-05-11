package com.auction.pro.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.auction.pro.account.dto.AccountDto;
import com.auction.pro.common.dao.AbstractDAOImpl;
import com.auction.pro.user.dao.base.UserAccountMapDao;
import com.auction.pro.user.model.AccountUserMap;

@Repository
public class UserAccountMapDaoImpl extends AbstractDAOImpl<AccountUserMap>
		implements UserAccountMapDao {
	@Autowired
	MongoTemplate mongoTemplate;

	public List<String> findUserIdByAccountId(List<AccountDto> accountDtos) {
		// TODO Auto-generated method stub
		List<String> userIds = new ArrayList<String>();
		List<Criteria> criterias = new ArrayList<Criteria>();
		for (AccountDto accountDTO : accountDtos) {
			criterias.add(new Criteria("accountId").regex(accountDTO.getId()));
		}
		Criteria criteria = new Criteria().orOperator(criterias
				.toArray(new Criteria[criterias.size()]));
		for (AccountUserMap accountUserMap : mongoTemplate.find(new Query(
				criteria), AccountUserMap.class)) {
			userIds.add(accountUserMap.getUserId());
		}
		return userIds;
	}

	@Override
	public boolean isExists(AccountUserMap entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
