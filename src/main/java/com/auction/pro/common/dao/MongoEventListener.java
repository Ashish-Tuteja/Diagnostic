package com.auction.pro.common.dao;

/**
 * Maintain auditing of Cimble
 */
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;

import com.auction.pro.common.controller.AbstractController;
import com.auction.pro.common.model.BaseModel;
import com.auction.pro.user.model.UserIdentity;

public class MongoEventListener<T extends BaseModel> extends
		AbstractMongoEventListener<T> {
	Long createdAt = 0L;
	Long lastmodified = 0L;
	String user = "";
	@Autowired
	AbstractController abstractController;

	@Override
	public void onBeforeConvert(T source) {
		// TODO Auto-generated method stub
		UserIdentity identity = abstractController.currentUserNameByPrincipal();
		user = identity != null ? identity.getUsername() == null ? identity
				.getEmailId() : identity.getUsername() : "";
		if (source.getCreatedAt() == null) {
			createdAt = Calendar.getInstance().getTimeInMillis();
			source.setCreatedAt(createdAt);
			source.setCreatedBy(user);
		}
		lastmodified = Calendar.getInstance().getTimeInMillis();
		source.setLastModifiedBy(user);
		source.setLastmodified(lastmodified);

	}
}
