package com.auction.pro.common.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.auction.pro.common.model.BaseModel;
import com.auction.pro.common.utils.CommonUtils;
import com.auction.pro.common.utils.PagedRequest;

public abstract class AbstractDAOImpl<T extends BaseModel> implements
		AbstractDAO<T> {

	@Autowired
	private MongoTemplate mongoTemplate;
	private Class<T> entityClass;

	public abstract boolean isExists(T entity);

	public AbstractDAOImpl() {
		super();
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass
				.getActualTypeArguments()[0];
	}

	public Page<T> findAllPage(Pageable pageable, List<String> fields) {
		List<T> list = null;
		Query query = new Query();
		query.with(pageable);
		// add projection
		addFields(fields, query);
		list = getMongoTemplate().find(query, entityClass);
		long total = getMongoTemplate().count(query, entityClass);
		Page<T> entityPage = new PageImpl<T>(list, pageable, total);
		return entityPage;
	}

	public Page<T> findAllPage(Pageable pageable) {
		return findAllPage(pageable, null);

	}

	public List<T> findAll() {
		return findAll(null);
	}

	public List<T> findAll(List<String> fields) {
		List<T> list = null;
		Query query = new Query();
		// add projection
		addFields(fields, query);

		list = getMongoTemplate().find(query, entityClass);

		return list;
	}

	public T findById(Serializable id) {
		return findById(id, null);
	}

	public T findById(Serializable id, List<String> fields) {
		Query query = new Query(Criteria.where("_id").is(id));
		addFields(fields, query);
		T entity = getMongoTemplate().findOne(query, entityClass);
		return entity;
	}

	public T save(T entity) {
		if (entity.getId() == null || entity.getId().length() <= 0) {
			entity.setId(CommonUtils.generateUUID());
		}
		getMongoTemplate().save(entity);
		return entity;
	}

	public void delete(T entity) {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("_id").is(entity.getId()));
		getMongoTemplate().remove(query, entityClass);
	}

	public int count(String collectionName) {
		return getMongoTemplate().getCollection(collectionName).find().count();
	}

	public void addFields(List<String> fields, Query query) {
		if (fields != null && query != null) {
			for (String field : fields) {
				query.fields().include(field);
			}
		}
	}

	public boolean exists(T entity) {
		return isExists(entity);
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
}
