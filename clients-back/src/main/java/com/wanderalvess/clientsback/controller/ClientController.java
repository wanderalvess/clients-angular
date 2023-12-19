package com.wanderalvess.clientsback.controller;

import com.wanderalvess.clientsback.model.Client;
import com.wanderalvess.clientsback.model.Phone;
import com.wanderalvess.clientsback.repository.ClientRepository;
import com.wanderalvess.clientsback.repository.PhoneRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

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
        validateDocument(client.getDocument());
        validateAddress(client.getAddress());
        validateLatitude(client.getLatitude());
        validateLongitude(client.getLongitude());
        validatePhone(client.getPhones());

        return clientRepository.save(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id,
                                         @RequestBody Client client) {
        validateName(client.getName());
        validateDocument(client.getDocument());
        validateAddress(client.getAddress());
        validateLatitude(client.getLatitude());
        validateLongitude(client.getLongitude());
        validatePhone(client.getPhones());

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
                throw new IllegalArgumentException("O campo de Nome do Cliente deve conter mais de 10 caracteres");
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

    private void validatePhone(List<Phone> phones) {
        String PHONE_REGEX = "\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}";

        if (phones == null || phones.isEmpty()) {
            throw new IllegalArgumentException("A lista de telefones não pode ser nula ou vazia.");
        }

        for (Phone phone : phones) {
            if (phone == null || isNullOrEmpty(phone.getNumber())) {
                throw new IllegalArgumentException("O campo de telefone é obrigatório para todos os telefones na lista.");
            } else if (phone.getNumber().length() < 10) {
                throw new IllegalArgumentException("O campo de telefone deve ter tamanho mínimo de 10 caracteres para o número: " + phone.getNumber());
            } else if (!Pattern.matches(PHONE_REGEX, phone.getNumber())) {
                throw new IllegalArgumentException("O formato do telefone é inválido para o número: " + phone.getNumber());
            } else if (phoneRepository.existsByNumber(phone.getNumber())) {
                throw new IllegalArgumentException("O número de telefone já está em uso: " + phone.getNumber());
            }
        }
    }

}
