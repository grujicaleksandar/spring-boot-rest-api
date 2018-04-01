package com.spring.rest.services;

import java.util.List;
import java.util.Optional;

import com.spring.rest.models.Person;

public interface PersonService {

	List<Person> getAll();
	
	Person getPersonById(long id);
	
	Person savePerson(Person p);
	
	Person updatePersonById(long id);
	
	Person deletePersonById(long id);
	
}
