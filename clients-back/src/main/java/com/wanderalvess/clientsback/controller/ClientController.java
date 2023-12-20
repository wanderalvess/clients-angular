package com.wanderalvess.clientsback.controller;

import com.wanderalvess.clientsback.model.Client;
import com.wanderalvess.clientsback.model.Phone;
import com.wanderalvess.clientsback.repository.ClientRepository;
import com.wanderalvess.clientsback.repository.PhoneRepository;
import com.wanderalvess.clientsback.util.util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController extends util {

    private final ClientRepository clientRepository;
    private final PhoneRepository phoneRepository;

    public ClientController(ClientRepository clientRepository, PhoneRepository phoneRepository) {
        this.clientRepository = clientRepository;
        this.phoneRepository = phoneRepository;
    }

    @GetMapping
    public List<Client> list() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable("id") Long id) {
        return clientRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Client create(@RequestBody Client client) {
        validateName(client.getName());
        validateDocument(client.getDocument(),clientRepository);
        validateAddress(client.getAddress());
        validatePhone(client.getPhones(), phoneRepository);
        for (Phone phone : client.getPhones()) {
            phone.setClient(client);
        }
        return clientRepository.save(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id,
                                         @RequestBody Client client) {
        validateName(client.getName());
       // validateDocument(client.getDocument(), clientRepository);
        validateAddress(client.getAddress());
        validatePhone(client.getPhones(), phoneRepository);

        return clientRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(client.getName());
                    recordFound.setDocument(client.getDocument());
                    recordFound.setAddress(client.getAddress());
                    recordFound.setLatitude(client.getLatitude());
                    recordFound.setLongitude(client.getLongitude());
                    recordFound.setNeighborhood(client.getNeighborhood());
                    for (Phone phone : client.getPhones()) {
                        phone.setClient(recordFound);
                    }
                    recordFound.setPhones(client.getPhones());
                    Client updated = clientRepository.save(recordFound);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(recordFound -> {
                    clientRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
