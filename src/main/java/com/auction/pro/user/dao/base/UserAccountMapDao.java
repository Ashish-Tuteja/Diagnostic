package com.auction.pro.user.dao.base;

import java.util.List;

import com.auction.pro.account.dto.AccountDto;
import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.user.model.AccountUserMap;

public interface UserAccountMapDao extends AbstractDAO<AccountUserMap> {
	// public <T> T findByUserId(Serializable id);

	public List<String> findUserIdByAccountId(List<AccountDto> accountDtos);

}
