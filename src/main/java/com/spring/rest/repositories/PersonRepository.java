package com.spring.rest.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring.rest.models.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{

}
