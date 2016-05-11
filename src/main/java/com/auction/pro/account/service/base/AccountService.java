package com.auction.pro.account.service.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auction.pro.account.dto.AccountDto;
import com.auction.pro.common.service.AbstractService;

/**
 * @author infoobjects
 *
 */
public interface AccountService extends AbstractService<AccountDto> {
	/**
	 * @param pageId
	 * @param userId
	 * @return list of accounts
	 */
	public int count();

	/* New */
	public AccountDto findByUserId(String userId) throws Exception;

	public void activation(String accountactivationId) throws Exception;

	public AccountDto findByAccountName(AccountDto accountDto) throws Exception;

	public List<AccountDto> findByParentAccountId(String parentId,
			List<String> fields) throws Exception;

	public AccountDto findByContactId(Serializable contactId) throws Exception;

	List<AccountDto> findBySerachterm(String searchterm,
			Serializable parentAccountId) throws Exception;

	public Page<AccountDto> findAllPage(Pageable pageable,
			String parentAccountId) throws Exception;
}
