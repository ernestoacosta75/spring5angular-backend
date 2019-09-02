package com.bolsadeideas.springboot.backend.apirest.controllers;

import com.bolsadeideas.springboot.backend.apirest.SpringBootBackendApirestApplication;
import com.bolsadeideas.springboot.backend.apirest.entities.Client;
import com.bolsadeideas.springboot.backend.apirest.services.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class extends the AbstracTest class to implements
 * the unit test for each method such GET, POST, PUT and DELETE
 * of the respective RestController.
 *
 * @author Ernesto Antonio Rodriguez Acosta
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringBootBackendApirestApplication.class})
@WebAppConfiguration
public class ClientRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    ClientService clientService;

    private MockMvc mockMvc;

    private static List<Client> clientList = new ArrayList<>();

    @BeforeClass
    public static void setupTestData() {
        clientList.add(Client.builder().id(1L).name("Ernesto")
                        .lastName("Acosta").email("eara@yahoo.it").createdAt(new Date()).build());

        clientList.add(Client.builder().id(2L).name("Paola")
                .lastName("Damiani").email("lottipau@yahoo.it").createdAt(new Date()).build());
    }

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesClientRestController() throws Exception {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webApplicationContext.getBean("clientRestController"));
    }

    @Test
    public void givenClientsPageURI_whenMockMvc_thenReturns() throws Exception {
        String uri = "/api/clients";

        Mockito.when(clientService.findAll()).thenReturn(clientList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri)
                                        .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        List<Client> clients = objectMapper.readValue(content, List.class);

        Assert.assertNotNull(content);
        Assert.assertTrue(clients.size() == 2);
    }

    @Test
    public void givenClientToSave_whenMockMvc_thenReturns_ClientEntitySaved() throws Exception {

    }

}
