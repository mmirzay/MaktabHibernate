package day0827;

import day0827.dao.AbstractDao;
import day0827.dao.ContactDao;
import day0827.entities.Contact;

public class Main {
	public static void main(String[] args) {

		AbstractDao<Contact, Integer> dao = new ContactDao();
//		Contact contact = new Contact("ali", "alimail");
//		dao.save(contact);

		dao.findByNamePrefix("ali").stream().forEach(System.out::println);

	}
}