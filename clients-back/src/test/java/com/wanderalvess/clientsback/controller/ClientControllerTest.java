package com.wanderalvess.clientsback.controller;

import com.wanderalvess.clientsback.model.Client;
import com.wanderalvess.clientsback.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientRepository clientRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    public void testList() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(1L, "João Ferreira", "12345678901", "Rua 1, Goiânia -GO", "10.12345", "20.67890"));
        clients.add(new Client(2L, "Maria ferreira", "98765432101", "Rua 2, Goiânia -GO", "30.98765", "40.54321"));
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = clientController.list();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("João Ferreira", result.get(0).getName());
        assertEquals("Maria ferreira", result.get(1).getName());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {

        Long clientId = 1L;
        Client client = new Client(clientId, "João Ferreira", "12345678901", "Rua 1, Goiânia -GO", "10.12345", "20.67890");
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        ResponseEntity<Client> result = clientController.findById(clientId);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("João Ferreira", result.getBody().getName());
        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    public void testCreateClient() throws Exception {
        // Criação de um objeto Client para o teste
        Client client = new Client();
        client.setName("João Ferreira");
        client.setDocument("12345678900");
        client.setAddress("Rua 1, Goiânia -GO");
        client.setLatitude("10.12345");
        client.setLongitude("20.67890");

        when(clientRepository.save(any(Client.class))).thenReturn(client);

        String requestJson = "{\"name\":\"João Ferreira\",\"document\":\"12345678900\",\"address\":\"Rua 1, Goiânia -GO\",\"latitude\":\"10.12345\",\"longitude\":\"20.67890\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);

        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("João Ferreira"))
                .andDo(MockMvcResultHandlers.print()); // Correção feita aqui

        MvcResult mvcResult = resultActions.andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();

        verify(clientRepository, times(1)).save(any(Client.class));
    }
}