package com.bolsadeideas.springboot.backend.apirest.controllers;

import com.bolsadeideas.springboot.backend.apirest.entities.Client;
import com.bolsadeideas.springboot.backend.apirest.services.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClientRestController {

    @Autowired
    private ClientService clientService;

    @ApiOperation(value = "View a list of clients", response= List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/clients")
    public List<Client> index() {
        return clientService.findAll();
    }

    @ApiOperation(value = "View an specific client", response = Client.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved client"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/clients/{id}")
    public Client findById(@ApiParam(value = "The client id", required = true) @Valid @PathVariable Long id) {
        return clientService.findById(id);
    }

    @ApiOperation(value = "To save a client", response = Client.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully saved client"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@ApiParam(value = "The client to save in JSON format", required = true)  @RequestBody Client client) {
        return clientService.save(client);
    }

    @ApiOperation(value = "To update a client", response = Client.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updated client"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PutMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Client update(@ApiParam(value = "The client to update in JSON format", required = true)  @RequestBody Client client,
                         @ApiParam(value = "The client id", required = true) @Valid @PathVariable Long id) {

        Client currentClient = clientService.findById(id);

        currentClient.setLastName(client.getLastName());
        currentClient.setName(client.getName());
        currentClient.setEmail(client.getEmail());
        currentClient.setCreatedAt(new Date());

        return clientService.save(currentClient);
    }

    @ApiOperation(value = "To delete a client")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted client"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @DeleteMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@ApiParam(value = "The client id", required = true) @Valid @PathVariable Long id) {
        clientService.delete(id);
    }
}
