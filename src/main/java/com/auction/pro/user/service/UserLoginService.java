package com.auction.pro.user.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auction.pro.common.utils.CommonUtils;
import com.auction.pro.user.dao.base.RoleDao;
import com.auction.pro.user.dao.base.UserLoginDao;
import com.auction.pro.user.model.User;
import com.auction.pro.user.model.UserIdentity;

@SuppressWarnings("deprecation")
@Service
public class UserLoginService implements UserDetailsService {
	private UserIdentity userIdentity;
	private User user;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	UserLoginDao userDao;
	@Autowired
	RoleDao roleDao;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserLoginService.class.getName());

	/*
	 * loadUserByUsername validate User authenticity
	 */
	public UserDetails loadUserByUsername(String j_username)
			throws UsernameNotFoundException, DataAccessException {
		// TODO Auto-generated method stub
		user = new User();
		userIdentity = new UserIdentity();
		try {
			if (!CommonUtils.checkIsUsernameOrEmailId(j_username)) {
				user.setUserName(j_username);
				userIdentity.setUsername(user.getUserName());
			} else {
				user.setEmailid(j_username);
				userIdentity.setEmailId((user.getEmailid()));
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		try {
			user = userDao.findByUsername(user);

		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (userDao.getLoginAttempts(j_username) >= 3) {
				LOGGER.info("Attempts is 3");
				throw new LockedException("User account is locked");

			} else {
				if (user.getEmailid() != null || user.getUserName() != null) {
					try {
						userIdentity.setEmailId(user.getEmailid());
						userIdentity.setPassword(user.getPassword());
						userIdentity.setId(user.getId());
						userIdentity.setRoleId(user.getRoleId());
						userIdentity.setRolePermission(user
								.getUserPermissions());
						userIdentity.setId(user.getId());
						List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(
								2);
						authList.add(new GrantedAuthorityImpl("ROLE_USER"));
					} catch (Exception e) {
						e.printStackTrace();
					}
					return userIdentity;
				} else {
					throw new UsernameNotFoundException("No user");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UsernameNotFoundException("No user");
		}

	}
}
