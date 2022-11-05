package com.wanderalvess.clientsback.controller;

import com.wanderalvess.clientsback.model.Client;
import com.wanderalvess.clientsback.repository.ClientRepository;
import com.wanderalvess.clientsback.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public List<Client> list(){
        return clientRepository.findAll();
    }
}
