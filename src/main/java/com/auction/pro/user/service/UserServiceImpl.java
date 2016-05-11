package com.auction.pro.user.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.auction.pro.account.dao.base.AccountDao;
import com.auction.pro.account.model.Account;
import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.common.service.AbstractServiceImpl;
import com.auction.pro.common.utils.CommonUtils;
import com.auction.pro.user.dao.base.ContactDao;
import com.auction.pro.user.dao.base.PermissionDao;
import com.auction.pro.user.dao.base.RoleDao;
import com.auction.pro.user.dao.base.UserAccountMapDao;
import com.auction.pro.user.dao.base.UserDao;
import com.auction.pro.user.dao.base.UserLoginDao;
import com.auction.pro.user.dto.RoleDto;
import com.auction.pro.user.dto.UserDto;
import com.auction.pro.user.model.AccountUserMap;
import com.auction.pro.user.model.Contact;
import com.auction.pro.user.model.Permission;
import com.auction.pro.user.model.Role;
import com.auction.pro.user.model.User;
import com.auction.pro.user.service.base.UserService;

@Service
public class UserServiceImpl extends AbstractServiceImpl<UserDto, User>
		implements UserService {
	@Autowired
	UserDao userDao;
	@Autowired
	UserAccountMapDao userAccountMapDao;
	@Autowired
	AccountDao accountDao;
	@Autowired
	UserLoginDao userLoginDao;
	@Autowired
	ContactDao contactDao;
	@Autowired
	PermissionDao permissionDao;
	@Autowired
	RoleDao roleDao;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserServiceImpl.class.getName());

	public void delete(UserDto entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public AbstractDAO<User> getDAO() {
		// TODO Auto-generated method stub
		return userDao;
	}

	@Override
	public UserDto getDTOForEntity(User entity) {
		// TODO Auto-generated method stub
		return new UserDto(entity);
	}

	@Override
	public List<UserDto> getDTOsForEntities(Iterable<User> entities) {
		Iterator<User> iterateuser = entities.iterator();
		List<UserDto> userDTOs = new ArrayList<UserDto>();
		User user;
		while (iterateuser.hasNext()) {
			user = (User) iterateuser.next();
			userDTOs.add(getDTOForEntity(user));
		}
		return userDTOs;
	}

	public List<UserDto> findBySerachterm(String searchterm,
			List<String> userIds) throws Exception {
		return getDTOsForEntities(userDao.findBySerachterm(searchterm, userIds));
	}

	public UserDto save(UserDto entity) {
		// TODO Auto-generated method stub
		if (entity.getAddress().getId() == null) {

			boolean chekIfExists = userDao.exists((User) getEntityFromDTO(
					entity, User.class));
			if (!chekIfExists) {
				if (entity.getRoleId() == null) {
					entity.setRoleId("role1");
					entity.setRolePermission(roleDao.findPermissionsByRoleId(
							"role1").getPermissionId());
				} else {
					entity.setRolePermission(roleDao.findPermissionsByRoleId(
							entity.getRoleId()).getPermissionId());
				}

				return saveEntity(entity);
			} else {
				LOGGER.info("user exists");
				return null;
			}
		} else {
			List<String> permissions = new ArrayList<String>();
			try {
				for (Map.Entry<String, Boolean> entry : entity
						.getPermissionsMap().entrySet()) {
					if (entry.getValue()) {
						permissions.add(entry.getKey());
					}
				}
				entity.setRolePermission(permissions);
				userDao.setPermissions((User) getEntityFromDTO(entity,
						User.class));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			contactDao.save((Contact) getEntityFromDTO(entity, Contact.class));
			return entity;
		}

	}

	public void disableLoginAttempts(String user) {
		// TODO Auto-generated method stub
		userLoginDao.disableLoginAttempts(user);
	}

	public List<Account> findByAccountId(String id) throws Exception {
		// TODO Auto-generated method stub
		return userDao.findByAccountId(id);
	}

	public <T> T findByAccount(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getEntityFromDTO(UserDto entity,
			@SuppressWarnings("rawtypes") Class classType) {
		if (classType.getSimpleName().equals("Account")) {
			return new Account(entity);
		} else if (classType.getSimpleName().equals("Contact")) {
			return new Contact(entity);

		} else if (classType.getSimpleName().equals("User")) {
			return new User(entity);
		} else {
			return new AccountUserMap(entity);
		}

	}

	public Contact findContactById(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		return userDao.findContactById(id);
	}

	private UserDto saveEntity(UserDto entity) {
		entity.setContactId(contactDao.save(
				(Contact) getEntityFromDTO(entity, Contact.class)).getId());
		// save user
		entity.setPassword(CommonUtils.encryption(entity.getPassword()));
		User user = userDao.save((User) getEntityFromDTO(entity, User.class));
		entity.setUserId(user.getId());
		// set AccountID and ActivationID
		try {
			if (entity.getParentAccountId() != null) {
				List<String> parentAccountIds = accountDao.findById(
						entity.getParentAccountId()).getParentAccountId();
				parentAccountIds.add(entity.getParentAccountId());
				entity.setParentAccountIds(parentAccountIds);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Account account = accountDao.save((Account) getEntityFromDTO(entity,
				Account.class));
		entity.setAccountId(account.getId());

		userAccountMapDao.save((AccountUserMap) getEntityFromDTO(entity,
				AccountUserMap.class));
		UserDto dto = getDTOForEntity(user);
		dto.setAccountActivationId(account.getActivationId());
		LOGGER.info("User save ");
		return dto;
	}

	public Map<String, Boolean> findPermissions(UserDto userDto) {
		// TODO Auto-generated method stub
		List<String> allpermissions = iteratePermissionsName(permissionDao
				.findPermissions());
		Map<String, Boolean> permissionsMap = new HashMap<String, Boolean>();
		for (String permission : allpermissions) {
			if (userDto.getRolePermission().contains(permission)) {
				permissionsMap.put(permission, true);
			} else {
				permissionsMap.put(permission, false);
			}
		}
		return permissionsMap;
	}

	public List<String> iteratePermissionsName(Iterable<Permission> entities) {

		Iterator<Permission> iterateuser = entities.iterator();
		List<String> permissionDTOs = new ArrayList<String>();
		Permission permission;
		while (iterateuser.hasNext()) {
			permission = (Permission) iterateuser.next();
			permissionDTOs.add(permission.getName());
		}
		return permissionDTOs;
	}

	public List<RoleDto> getAllRoles() {
		// TODO Auto-generated method stub
		return iterateRoles(roleDao.findAll());
	}

	public List<RoleDto> iterateRoles(Iterable<Role> entities) {
		Iterator<Role> iterateuser = entities.iterator();
		List<RoleDto> roleDTOs = new ArrayList<RoleDto>();
		Role role;
		RoleDto roleDto;
		while (iterateuser.hasNext()) {
			role = (Role) iterateuser.next();
			roleDto = new RoleDto();
			roleDto.setId(role.getId());
			roleDto.setName(role.getName());
			roleDto.setPermissionId(role.getPermissionId());
			roleDTOs.add(roleDto);
		}
		return roleDTOs;
	}

	public boolean updatePassword(UserDto userDTO) throws Exception {
		// TODO Auto-generated method stub
		return userDao
				.setPassword((User) getEntityFromDTO(userDTO, User.class));
	}

	public Page<UserDto> findAllPage(Pageable pageable, List<String> userIds) {
		// TODO Auto-generated method stub
		Page<UserDto> page = convertEntityPageToDTOPage(
				userDao.findAllPage(pageable, userIds), pageable);
		List<UserDto> usersDTOs = new ArrayList<UserDto>();
		for (UserDto user : page.getContent()) {
			try {
				user.setAddress(findContactById(user.getContactId()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOGGER.error(e.getMessage());
			}
			user.setPermissionsMap(findPermissions(user));
			usersDTOs.add(user);
		}
		return new PageImpl<UserDto>(usersDTOs, pageable,
				page.getTotalElements());
	}

	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
	}
}