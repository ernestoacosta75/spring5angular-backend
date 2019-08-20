package com.bolsadeideas.springboot.backend.apirest.services.impl;

import com.bolsadeideas.springboot.backend.apirest.dao.ClientDao;
import com.bolsadeideas.springboot.backend.apirest.entities.Client;
import com.bolsadeideas.springboot.backend.apirest.services.ClientService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceImplTest {

    @Autowired
    ClientService clientService;

    @MockBean
    ClientDao clientDao;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFindById() throws Exception {
        Client client = Client.builder().id(1L).name("Ernesto").lastName("Acosta").email("eara@yahoo.it").build();

        when(clientDao.findById(1L)).thenReturn(Optional.ofNullable(client));

        Client queryResult = clientService.findById(1L);

        assertTrue(queryResult.getName().equalsIgnoreCase("Ernesto"));
        assertNotNull(queryResult);
    }

    @Test
    public void testFindAll_And_Convert_Names_ToUpperCase() {
        Iterable<Client> iterable = Arrays.asList(Client.builder()
                        .name("Ernesto")
                        .id(1L).lastName("Acosta")
                        .email("ernesto@yahoo.it")
                        .createdAt(new Date())
                        .build(),
                Client.builder()
                        .name("Paola")
                        .id(1L).lastName("Damiani")
                        .email("paola@yahoo.it")
                        .createdAt(new Date())
                        .build()
        );

        List<String> result = StreamSupport.stream(iterable.spliterator(), false)
                .map(client -> client.getName().toUpperCase()).collect(Collectors.toList());

        assertThat(result).contains("ERNESTO", "PAOLA");
    }
}