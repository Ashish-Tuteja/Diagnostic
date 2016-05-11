package com.auction.pro.user.service.base;

import java.util.List;

import com.auction.pro.account.dto.AccountDto;
import com.auction.pro.common.service.AbstractService;
import com.auction.pro.user.dto.AccountUserMapDto;

public interface UserAccountMapService extends
		AbstractService<AccountUserMapDto> {
	public List<String> findUserIdByAccountId(List<AccountDto> accountDtos);
}
