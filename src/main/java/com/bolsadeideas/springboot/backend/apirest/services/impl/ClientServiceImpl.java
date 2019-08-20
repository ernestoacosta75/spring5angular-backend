package com.bolsadeideas.springboot.backend.apirest.services.impl;

import com.bolsadeideas.springboot.backend.apirest.dao.ClientDao;
import com.bolsadeideas.springboot.backend.apirest.entities.Client;
import com.bolsadeideas.springboot.backend.apirest.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Override
    @Transactional(readOnly = true)
    public Stream<Client> findAll() {
        return StreamSupport.stream(clientDao.findAll().spliterator(), false);
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(Long id) {
        return clientDao.findById(id).get();
    }
}
