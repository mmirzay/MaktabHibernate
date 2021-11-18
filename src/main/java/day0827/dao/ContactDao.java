package day0827.dao;

import day0827.entities.Contact;

public class ContactDao extends AbstractDao<Contact, Integer> {

	@Override
	protected Class<Contact> getEntityClass() {
		return Contact.class;
	}

}
