package com.auction.pro.common.utils;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PagedRequest implements Serializable, Pageable {

	private static final long serialVersionUID = -1713245578898613491L;

	private int page;
	private int size;
	private String filter;

	public PagedRequest() {
		// TODO Auto-generated constructor stub
	}

	public PagedRequest(int page, int size, int offset) {
		// TODO Auto-generated constructor stub
		this.page = page;
		this.size = size;

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

	public Sort getSort() {
		// TODO Auto-generated method stub
		return null;
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

}
