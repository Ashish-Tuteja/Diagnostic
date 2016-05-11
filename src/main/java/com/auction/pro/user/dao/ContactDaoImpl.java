package com.auction.pro.user.dao;

import org.springframework.stereotype.Service;

import com.auction.pro.common.dao.AbstractDAOImpl;
import com.auction.pro.user.dao.base.ContactDao;
import com.auction.pro.user.model.Contact;

@Service
public class ContactDaoImpl extends AbstractDAOImpl<Contact> implements
		ContactDao {

	@Override
	public boolean isExists(Contact entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
