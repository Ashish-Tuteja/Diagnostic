package com.auction.pro.user.dao.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.user.model.Contact;
import com.auction.pro.user.model.User;

public interface UserDao extends AbstractDAO<User> {
	public List<User> findBySerachterm(String searchterm, List<String> userIds)
			throws Exception;

	public Contact findContactById(Serializable id) throws Exception;

	public <Account> List<Account> findByAccountId(String id) throws Exception;

	public User setPermissions(User user) throws Exception;

	public boolean setPassword(User user) throws Exception;

	public Page<User> findAllPage(Pageable pageable, List<String> userIds);

}
