package com.spring.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.exceptions.PersonNotFoundException;
import com.spring.rest.models.Person;
import com.spring.rest.services.PersonService;

@RestController
public class PersonController {

	private PersonService service;

	@Autowired
	public PersonController(PersonService service) {
		this.service = service;
	}

	@GetMapping("/persons")
	public List<Person> getAll() {

		List<Person> persons = service.getAll();

		return persons;

	}

	@GetMapping("/persons/{id}")
	public ResponseEntity<Resource<Person>> getPersonById(@PathVariable long id) {

		Person p = service.getPersonById(id);

		Resource<Person> resource = new Resource<Person>(p);

		ControllerLinkBuilder linkToPerson = linkTo(methodOn(this.getClass()).getPersonById(id));
		ControllerLinkBuilder linkToPersons = linkTo(methodOn(this.getClass()).getAll());

		resource.add(linkToPerson.withRel("self"));
		resource.add(linkToPersons.withRel("all"));

		if (p != null)
			return new ResponseEntity(resource, HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NOT_FOUND);

	}

	@PostMapping("/persons")
	public ResponseEntity<Object> savePerson(@RequestBody Person person) {

		return new ResponseEntity<>(service.savePerson(person), HttpStatus.CREATED);

	}

	@PutMapping("/persons/{id}")
	public Person updatePerson(@PathVariable long id, @RequestBody Person person) {

		Person p = service.getPersonById(id);

		p.setFirstName(person.getFirstName());
		p.setLastName(person.getLastName());
		p.setEmail(person.getEmail());
		p.setCountry(person.getCountry());

		service.savePerson(p);

		return p;

	}

	@DeleteMapping("/persons/{id}")
	public ResponseEntity<Object> deletePersonById(@PathVariable long id) {

		return new ResponseEntity<>(service.deletePersonById(id), HttpStatus.NO_CONTENT);

	}

}
