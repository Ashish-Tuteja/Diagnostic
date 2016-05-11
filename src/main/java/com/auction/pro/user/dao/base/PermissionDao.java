package com.auction.pro.user.dao.base;

import java.util.List;

import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.user.model.Permission;

public interface PermissionDao extends AbstractDAO<Permission> {
	public List<Permission> findPermissions();

}
