package com.auction.pro.user.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.common.service.AbstractServiceImpl;
import com.auction.pro.user.dao.base.UserLoggingDao;
import com.auction.pro.user.dto.UserLogDto;
import com.auction.pro.user.model.UserLog;
import com.auction.pro.user.service.base.UserLoggingService;

@Service
public class UserLoggingServiceImpl extends
		AbstractServiceImpl<UserLogDto, UserLog> implements UserLoggingService {
	@Autowired
	UserLoggingDao userLoggingDao;

	public UserLogDto save(UserLogDto entity) {
		// TODO Auto-generated method stub
		return getDTOForEntity(userLoggingDao.save((UserLog) getEntityFromDTO(
				entity, UserLog.class)));
	}

	public void delete(UserLogDto entity) {
		// TODO Auto-generated method stub

	}

	public UserLogDto findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserLogDto> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	public List<UserLogDto> findAll(List<String> fields) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractDAO<UserLog> getDAO() {
		// TODO Auto-generated method stub
		return userLoggingDao;
	}

	@Override
	public UserLogDto getDTOForEntity(UserLog entity) {
		// TODO Auto-generated method stub
		return new UserLogDto(entity);
	}

	@Override
	public Object getEntityFromDTO(UserLogDto entity,
			@SuppressWarnings("rawtypes") Class classType) {

		return new UserLog(entity);
	}

	@Override
	public List<UserLogDto> getDTOsForEntities(Iterable<UserLog> entities) {
		// TODO Auto-generated method stub
		Iterator<UserLog> iterator = entities.iterator();
		List<UserLogDto> userLogDTOs = new ArrayList<UserLogDto>();
		while (iterator.hasNext()) {
			userLogDTOs.add(getDTOForEntity(iterator.next()));
		}
		return userLogDTOs;
	}
}
