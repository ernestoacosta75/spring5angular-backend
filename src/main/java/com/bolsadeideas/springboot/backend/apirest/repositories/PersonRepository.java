package com.bolsadeideas.springboot.backend.apirest.repositories;

import com.bolsadeideas.springboot.backend.apirest.entities.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    Person findByName(String name);
}
