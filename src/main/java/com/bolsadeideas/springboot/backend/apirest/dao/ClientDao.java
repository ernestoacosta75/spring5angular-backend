package com.bolsadeideas.springboot.backend.apirest.dao;

import com.bolsadeideas.springboot.backend.apirest.entities.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientDao extends CrudRepository<Client, Long> {
}
