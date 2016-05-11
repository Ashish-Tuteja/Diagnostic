package com.auction.pro.user.service.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auction.pro.account.model.Account;
import com.auction.pro.common.service.AbstractService;
import com.auction.pro.user.dto.RoleDto;
import com.auction.pro.user.dto.UserDto;
import com.auction.pro.user.model.Contact;

public interface UserService extends AbstractService<UserDto> {
	// public UserAccount getUserAccountFromDto(UserDto userDto);
	List<UserDto> findBySerachterm(String searchterm, List<String> userIds)
			throws Exception;

	public <T> T findByAccount(String userId) throws Exception;

	public void disableLoginAttempts(String user) throws Exception;

	public Contact findContactById(Serializable id) throws Exception;

	public List<RoleDto> getAllRoles() throws Exception;

	public List<Account> findByAccountId(String id) throws Exception;

	public Map<String, Boolean> findPermissions(UserDto userDto)
			throws Exception;

	public boolean updatePassword(UserDto userDto) throws Exception;

	public Page<UserDto> findAllPage(Pageable pageable, List<String> userIds);

}
