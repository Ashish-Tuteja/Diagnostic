package com.auction.pro.common.utils;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PagedResponse<T> extends PageImpl<T> {

	public PagedResponse(List<T> content, Pageable pageable, long total) {
		super(content, pageable, total);
		// TODO Auto-generated constructor stub
	}

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

}
