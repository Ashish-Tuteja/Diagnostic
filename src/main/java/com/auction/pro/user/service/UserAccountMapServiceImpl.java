package com.auction.pro.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auction.pro.account.dto.AccountDto;
import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.common.service.AbstractServiceImpl;
import com.auction.pro.user.dao.base.UserAccountMapDao;
import com.auction.pro.user.dto.AccountUserMapDto;
import com.auction.pro.user.model.AccountUserMap;
import com.auction.pro.user.service.base.UserAccountMapService;

@Service
public class UserAccountMapServiceImpl extends
		AbstractServiceImpl<AccountUserMapDto, AccountUserMap> implements
		UserAccountMapService {
	@Autowired
	UserAccountMapDao userAccountMapDao;

	public AccountUserMapDto save(AccountUserMapDto entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(AccountUserMapDto entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public AbstractDAO<AccountUserMap> getDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountUserMapDto getDTOForEntity(AccountUserMap entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountUserMapDto> getDTOsForEntities(
			Iterable<AccountUserMap> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> findUserIdByAccountId(List<AccountDto> accountDtos) {
		// TODO Auto-generated method stub
		return userAccountMapDao.findUserIdByAccountId(accountDtos);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getEntityFromDTO(AccountUserMapDto entity, Class classType) {
		// TODO Auto-generated method stub
		return null;
	}

}
