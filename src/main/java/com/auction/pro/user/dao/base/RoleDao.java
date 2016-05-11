package com.auction.pro.user.dao.base;

import java.io.Serializable;

import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.user.model.Role;

public interface RoleDao extends AbstractDAO<Role> {

	public Role findPermissionsByRoleId(Serializable id);
}
