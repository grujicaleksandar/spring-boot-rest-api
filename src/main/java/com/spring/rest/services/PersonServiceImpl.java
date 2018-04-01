package com.spring.rest.services;


import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.spring.rest.exceptions.PersonNotFoundException;
import com.spring.rest.models.Person;
import com.spring.rest.repositories.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{

	private PersonRepository personRepository;

	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public List<Person> getAll() {
		
		return (List<Person>) personRepository.findAll();
	}

	@Override
	public Person getPersonById(long id) {
		
		Person person = personRepository.findById(id).orElse(null);
		
		return person;
	}

	@Override
	public Person savePerson(Person p) {
		
		Person person = personRepository.save(p);
		
		return person;
	}

	@Override
	public Person updatePersonById(long id) {
		
		Person person = personRepository.findById(id).orElse(null);
		
		personRepository.save(person);
		
		return person;
	}

	@Override
	public Person deletePersonById(long id) {
	
		Person person = personRepository.findById(id).orElse(null);
		
		personRepository.delete(person);
		
		return person;
		
	}
	
	
	
	
}
