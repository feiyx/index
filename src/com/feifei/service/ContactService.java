package com.feifei.service;

import com.feifei.dao.ContactDao;
import com.feifei.po.Contact;

public class ContactService {

	public int insertAll(Contact contact) {
		ContactDao dao = new ContactDao();
		return dao.insertAll(contact);
	}
}
