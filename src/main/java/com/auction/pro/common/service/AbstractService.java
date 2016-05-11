package com.auction.pro.common.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auction.pro.common.dto.BaseDTO;

public interface AbstractService<T extends BaseDTO> {
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
	 * Remove the entity from database
	 * 
	 * @param id
	 *            entity to remove
	 */
	public void deleteById(Serializable id);
	

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
}
