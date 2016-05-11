package com.auction.pro.account.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.auction.pro.account.dao.AccountDaoImpl;
import com.auction.pro.account.dao.base.AccountDao;
import com.auction.pro.account.dto.AccountDto;
import com.auction.pro.account.model.Account;
import com.auction.pro.account.service.base.AccountService;
import com.auction.pro.common.constants.MongoConstant;
import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.common.service.AbstractServiceImpl;
import com.auction.pro.user.dao.base.ContactDao;
import com.auction.pro.user.dao.base.UserAccountMapDao;
import com.auction.pro.user.model.AccountUserMap;
import com.auction.pro.user.model.Contact;

@Service
public class AccountServiceImpl extends
		AbstractServiceImpl<AccountDto, Account> implements AccountService {
	@Autowired
	AccountDao accountDao;
	@Autowired
	ContactDao contactDao;
	@Autowired
	UserAccountMapDao userAccountMapDao;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AccountServiceImpl.class.getName());

	public AccountDto save(AccountDto entity) {
		// TODO Auto-generated method stub
		try {

			if (entity.getParentAccountId() != null) {
				List<String> parentAccountIds = accountDao.findById(
						entity.getParentAccountId()).getParentAccountId();
				parentAccountIds.add(entity.getParentAccountId());
				entity.setParentAccountIds(parentAccountIds);
			}
			entity.setContactId(contactDao.save(
					(Contact) getEntityFromDTO(entity, Contact.class)).getId());
			AccountDto accountDto = save((Account) getEntityFromDTO(entity,
					Account.class));
			if (entity.getId() == null) {
				// set Account Id For account user map
				entity.setId(accountDto.getId());
				userAccountMapDao.save((AccountUserMap) getEntityFromDTO(
						entity, AccountUserMap.class));
			}
			return accountDto;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}

	}

	public void delete(AccountDto entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public AbstractDAO<Account> getDAO() {
		// TODO Auto-generated method stub
		return accountDao;
	}

	@Override
	public AccountDto getDTOForEntity(Account entity) {
		return new AccountDto(entity);
	}

	@Override
	public List<AccountDto> getDTOsForEntities(Iterable<Account> entities) {
		// TODO Auto-generated method stub
		Iterator<Account> accountEntity = entities.iterator();
		List<AccountDto> accountDTOs = new ArrayList<AccountDto>();
		Account account;
		while (accountEntity.hasNext()) {
			account = (Account) accountEntity.next();
			accountDTOs.add(getDTOForEntity(account));
		}

		return accountDTOs;
	}

	public Account getEntityFromDTO(AccountDto entity) {
		// TODO Auto-generated method stub
		Account accEntity = new Account(entity);
		return accEntity;
	}

	public int count() {
		// TODO Auto-generated method stub
		return accountDao.count(MongoConstant.ACCOUNT);
	}

	@Override
	public List<AccountDto> findAll() {
		// TODO Auto-generated method stub
		return getDTOsForEntities(accountDao.findAll());
	}

	public AccountDto findByUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		return getDTOForEntity(accountDao.findByUserId(userId));
	}

	public void activation(String activationId) throws Exception {
		// TODO Auto-generated method stub
		accountDao.activation(activationId);
	}

	public Page<AccountDto> findByFilter(Pageable pageable) {
		return convertEntityPageToDTOPage(accountDao.findAllPage(pageable),
				pageable);
	}

	@SuppressWarnings({ "unchecked" })
	public static <T, S> T getEntity(Class<S> class1) {
		return (T) new AccountDaoImpl();
	}

	@Override
	public Object getEntityFromDTO(AccountDto entity,
			@SuppressWarnings("rawtypes") Class classType) {
		if (classType.getSimpleName().equals("Account")) {
			return new Account(entity);
		} else if (classType.getSimpleName().equals("Contact")) {
			return new Contact(entity);
		} else {
			return new AccountUserMap(entity);
		}

	}

	public AccountDto findByAccountName(AccountDto accountDto) throws Exception {
		// TODO Auto-generated method stub
		return getDTOForEntity(accountDao
				.findByAccountName((Account) getEntityFromDTO(accountDto,
						Account.class)));
	}

	public List<AccountDto> findByParentAccountId(String parentId,
			List<String> fields) throws Exception {
		// TODO Auto-generated method stub
		return getDTOsForEntities(accountDao.findByParentAccountId(parentId,
				fields));
	}

	public List<AccountDto> findBySerachterm(String searchterm,
			Serializable parentAccountId) throws Exception {
		// TODO Auto-generated method stub
		return getDTOsForEntities(accountDao.findBySerachterm(searchterm,
				parentAccountId));
	}

	public AccountDto findByContactId(Serializable contactId) throws Exception {
		// TODO Auto-generated method stub
		return getDTOForEntity(accountDao.findByContactId(contactId));
	}

	public Page<AccountDto> findAllPage(Pageable pageable,
			String parentAccountId) throws Exception {
		// TODO Auto-generated method stub

		Page<AccountDto> page = convertEntityPageToDTOPage(
				accountDao.findAllPage(pageable, parentAccountId), pageable);

		List<AccountDto> accounts = new ArrayList<AccountDto>();
		for (AccountDto account : page.getContent()) {
			account.setAddress(contactDao.findById(account.getContactId()));
			accounts.add(account);
		}
		return new PageImpl<AccountDto>(accounts, pageable,
				page.getTotalElements());
	}
}
