package com.auction.pro.common.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.auction.pro.common.dao.AbstractDAO;
import com.auction.pro.common.dto.BaseDTO;
import com.auction.pro.common.model.BaseModel;

public abstract class AbstractServiceImpl<T extends BaseDTO, E extends BaseModel>
		implements AbstractService<T> {

	public abstract AbstractDAO<E> getDAO();

	public abstract T getDTOForEntity(E entity);

	public abstract Object getEntityFromDTO(T entity,
			@SuppressWarnings("rawtypes") Class classType);

	public abstract List<T> getDTOsForEntities(Iterable<E> entities);

	public Page<T> convertEntityPageToDTOPage(Page<E> page, Pageable pageable) {
		if (page != null) {
			List<T> dtos = getDTOsForEntities(page.getContent());
			return new PageImpl<T>(dtos, pageable, page.getTotalElements());
		} else {
			return new PageImpl<T>(new ArrayList<T>());
		}
	}

	public T save(E entity) {
		E e = getDAO().save(entity);
		return getDTOForEntity(e);
	}

	public void delete(E entity) {
		getDAO().delete(entity);
	}

	public T findById(Serializable id) {
		E e = getDAO().findById(id);
		return getDTOForEntity(e);
	}

	public Page<T> findAllPage(Pageable pageable, List<String> fields) {
		Page<E> page = getDAO().findAllPage(pageable, fields);
		return convertEntityPageToDTOPage(page, pageable);
	}

	public Page<T> findAllPage(Pageable pageable) {
		Page<E> page = getDAO().findAllPage(pageable);
		return convertEntityPageToDTOPage(page, pageable);
	}

	public List<T> findAll() {
		List<E> entities = getDAO().findAll();
		return getDTOsForEntities(entities);
	}

	public List<T> findAll(List<String> fields) {
		List<E> entities = getDAO().findAll(fields);
		return getDTOsForEntities(entities);
	}

}
