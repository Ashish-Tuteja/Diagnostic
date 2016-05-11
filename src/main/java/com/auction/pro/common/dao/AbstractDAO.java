package com.auction.pro.common.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auction.pro.common.model.BaseModel;

/**
 * The <code>IDaoBase</code> interface contains all the basic CRUD methods for
 * <code>T</code> entity. Here T is Generic
 */
public interface AbstractDAO<T extends BaseModel> {

	/**
	 * Save the entity
	 * 
	 * @param entity
	 *            entity to save
	 */
	public T save(T entity);

	/**
	 * Remove the entity from database
	 * 
	 * @param entity
	 *            entity to remove
	 */
	public void delete(T entity);

	/**
	 * @param id
	 * @return
	 */
	public T findById(Serializable id);

	/**
	 * @param pageable
	 * @param fields
	 * @return paged list for this entity
	 */
	public Page<T> findAllPage(Pageable pageable, List<String> fields);

	/**
	 * @param pageable
	 * @return paged list for this entity
	 */
	public Page<T> findAllPage(Pageable pageable);

	/**
	 * @return list for this entity
	 */
	public List<T> findAll();

	/**
	 * @param fields
	 * @return list for this entity
	 */
	public List<T> findAll(List<String> fields);

	/*
	 * Count Of Collection
	 */
	public int count(String collectionName);

	public boolean exists(T entity);

}