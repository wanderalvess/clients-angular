package com.wanderalvess.clientsback.service;

import com.wanderalvess.clientsback.model.Client;
import com.wanderalvess.clientsback.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
