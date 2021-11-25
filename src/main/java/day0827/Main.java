package day0827;

import day0827.dao.AbstractDao;
import day0827.dao.ContactDao;
import day0827.dao.PersonDao;
import day0827.entities.Contact;
import day0827.entities.Person;

public class Main {
	public static void main(String[] args) {

		AbstractDao<Contact, Integer> contactDao = new ContactDao();
		AbstractDao<Person, Integer> personDao = new PersonDao();
		Contact contact = new Contact("ali", "alimail");
//		contact.setId(1);
		contactDao.save(contact);
		Person person = new Person("Mohsen", "Mirzaei");
//		person.setId(1);
		personDao.save(person);

//		dao.findByNamePrefix("ali").stream().forEach(System.out::println);

	}
}