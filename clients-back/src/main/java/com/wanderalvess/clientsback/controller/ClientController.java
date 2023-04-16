package com.wanderalvess.clientsback.controller;

import com.wanderalvess.clientsback.model.Client;
import com.wanderalvess.clientsback.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.regex.Pattern;


import java.util.List;

@RestController
@RequestMapping("/api/clients")
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

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable("id") Long id) {
        return clientRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Client create(@RequestBody Client client){
        validateName(client.getName());
        validateDocument(client.getDocument());
        validateAddress(client.getAddress());
        validateLatitude(client.getLatitude());
        validateLongitude(client.getLongitude());
        
        return clientRepository.save(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id,
                                         @RequestBody Client client) {
        return clientRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(client.getName());
                    recordFound.setDocument(client.getDocument());
                    recordFound.setAddress(client.getAddress());
                    recordFound.setLatitude(client.getLatitude());
                    recordFound.setLongitude(client.getLongitude());
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

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private void validateDocument(String document) {
        if (isNullOrEmpty(document)) {
            throw new IllegalArgumentException("O campo de Documento (CPF/CNPJ) é obrigatório.");
        } else {
            String documentRegex = document.replaceAll("[/.-]", "");
                if (!Pattern.matches("\\d{11}|\\d{14}", documentRegex)) {
                    throw new IllegalArgumentException("O campo de Documento (CPF/CNPJ) deve ter tamanho mínimo de 11 caracteres e máximo de 14 caracteres.");
            }
        }
        return;
    }

    private void validateName(String name) {
        if (isNullOrEmpty(name)) {
            throw new IllegalArgumentException("O campo de Nome é obrigatório.");
        } else {
            if (name.length() < 4) {
                throw new IllegalArgumentException("O campo de Nome do Cliente deve conter mais de 4 caracteres");
            } 
        }
        return;
    }

    private void validateLatitude(String latitude) {
        if (isNullOrEmpty(latitude)) {
            throw new IllegalArgumentException("O campo de Latitude é obrigatório.");
        } else {
            if (latitude.length() < 5) {
                throw new IllegalArgumentException("O campo de Latitude deve ter tamanho mínimo de 5 caracteres.");
            } 
        }
        return;
    }
    private void validateLongitude(String longitude) {
        if (isNullOrEmpty(longitude)) {
            throw new IllegalArgumentException("O campo de longitude é obrigatório.");
        } else {
            if (longitude.length() < 5) {
                throw new IllegalArgumentException("O campo de longitude deve ter tamanho mínimo de 5 caracteres.");
            } 
        }
        return;
    }
    private void validateAddress(String address) {
        if (isNullOrEmpty(address)) {
            throw new IllegalArgumentException("O campo de Endereço é obrigatório.");
        } else {
            if (address.length() < 5) {
                throw new IllegalArgumentException("O campo de Endereço deve ter tamanho mínimo de 5 caracteres.");
            } 
        }
        return;
    }
}
