package com.bolsadeideas.springboot.backend.apirest.controllers;

import com.bolsadeideas.springboot.backend.apirest.entities.Client;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * This class extends the AbstracTest class to implements
 * the unit test for each method such GET, POST, PUT and DELETE
 * of the respective RestController.
 *
 * @author Ernesto Antonio Rodriguez Acosta
 */
public class ClientRestControllerTest  extends AbstractTest {

    @Test
    public void indexGETTest() throws Exception {
        String uri = "/api/clients";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Client [] clientsList = super.mapFromJson(content, Client[].class);
        Assert.assertTrue(clientsList.length > 0);

    }
}
