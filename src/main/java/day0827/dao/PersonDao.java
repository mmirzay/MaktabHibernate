package day0827.dao;

import day0827.entities.Person;

public class PersonDao extends AbstractDao<Person, Integer> {

	@Override
	protected Class<Person> getEntityClass() {
		return Person.class;
	}

}
