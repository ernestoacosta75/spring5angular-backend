package com.bolsadeideas.springboot.backend.apirest.controllers;

import com.bolsadeideas.springboot.backend.apirest.entities.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.ServletContext;


/**
 * This class extends the AbstracTest class to implements
 * the unit test for each method such GET, POST, PUT and DELETE
 * of the respective RestController.
 *
 * @author Ernesto Antonio Rodriguez Acosta
 */
public class ClientRestControllerTest extends AbstractTest {

    private static final Long CLIENT_ID = 1L;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesClientRestController() throws Exception {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webApplicationContext.getBean("clientRestController"));
    }

    @Test
    public void getClientsListTest() throws Exception {
        String uri = "/api/clients";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        Assert.assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Client [] clientList = super.mapFromJson(content, Client [].class);

        Assert.assertTrue(clientList.length > 0);
    }

    @Test
    public void showTest () throws Exception {
        String uri = "/api/clients/" + CLIENT_ID;

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        Assert.assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Client client = super.mapFromJson(content, Client.class);

        Assert.assertTrue(client.getId() == CLIENT_ID);
    }

    @Test
    public void saveTest () throws Exception {
        String uri = "/api/clients";

        Client client = Client.builder()
                        .id(20L).name("Test")
                        .lastName("Prova")
                        .email("test@prova.it")
                        .build();

        String inputJson = super.mapToJson(client);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(inputJson))
                                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        Assert.assertEquals(201, status);

        String content = mvcResult.getResponse().getContentAsString();
        Assert.assertTrue(content.split(",")[1].contains("Test"));
    }
}
