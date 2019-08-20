package com.bolsadeideas.springboot.backend.apirest.services;

import com.bolsadeideas.springboot.backend.apirest.entities.Client;

import java.util.stream.Stream;

public interface ClientService {

    public Stream<Client> findAll();

    public Client findById(Long id);
}
