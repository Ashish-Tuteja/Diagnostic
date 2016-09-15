package com.auction.pro.common.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PagedRequest implements Serializable, Pageable {

	private static final long serialVersionUID = -1713245578898613491L;

	private int page;
	private int size;
	private String filter;
	private Sort sort;
	private String sortFilter;
	
    
	public String getSortFilter() {
		return sortFilter;
	}

	public void setSortFilter(String sortFilter) {
		this.sortFilter = sortFilter;
		Map result = getSortFields(this.sortFilter);
		this.sort = new Sort(result.get("order") != null ? ((Sort.Direction)result.get("order")): Sort.Direction.ASC , result.get("field") != null ? result.get("field").toString() : null);
	}

	public PagedRequest() {
		// TODO Auto-generated constructor stub
	}

	public PagedRequest(int page, int size, int offset, Sort sort) {
		// TODO Auto-generated constructor stub
		this.page = page;
		this.size = size;
	}
	public PagedRequest(int page, int size) {
		// TODO Auto-generated constructor stub
		this.page = page;
		this.size = size;
	}

	public void setSort(Sort sort){
		this.sort=sort;

	}
	
	// sort is based on model fields not dto
	public int getPageNumber() {
		// TODO Auto-generated method stub
		return page;
	}

	public int getPageSize() {
		// TODO Auto-generated method stub
		return size;
	}

	public int getOffset() {
		// TODO Auto-generated method stub
		return page == 1 ? 0 : (size * (page - 1));
	}


	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public Sort getSort() {
		// TODO Auto-generated method stub
		return sort;
	}

	public Pageable first() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}

	public Pageable next() {
		// TODO Auto-generated method stub
		return null;
	}

	public Pageable previousOrFirst() {
		// TODO Auto-generated method stub
		return null;
	}
    
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private Map getSortFields(String sortField) {
		String sortFields[] = sortField.split(" ");
		Map result = new HashMap();
		for(String sF : sortFields){
			if(sF.equals("ASC")){
				result.put("order",Sort.Direction.ASC);
			} else if(sF.equals("DESC")){
				result.put("order",Sort.Direction.DESC);	
			} else {
				result.put("field", sF);
			}
		}
		return result;
	}
}
