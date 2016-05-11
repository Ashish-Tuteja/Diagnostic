package com.auction.pro.account.dao.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auction.pro.account.model.Account;
import com.auction.pro.common.dao.AbstractDAO;

public interface AccountDao extends AbstractDAO<Account> {
	public <T> T findAccountIdByUserId(String userId) throws Exception;

	public Account findByActivationId(String activationId) throws Exception;

	/* New */
	public void activation(String activationId) throws Exception;

	public Account findByAccountName(Account accountDto) throws Exception;

	public Account findByUserId(String userId) throws Exception;

	public List<Account> findByParentAccountId(String parentId,
			List<String> fields) throws Exception;

	public Account findByContactId(Serializable contactId) throws Exception;

	public List<Account> findBySerachterm(String searchterm,
			Serializable parentAccountId) throws Exception;

	public Page<Account> findAllPage(Pageable pageable, String parentAccountId)
			throws Exception;
}
