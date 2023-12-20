package com.wanderalvess.clientsback.controller;

import com.wanderalvess.clientsback.model.Client;
import com.wanderalvess.clientsback.model.Phone;
import com.wanderalvess.clientsback.repository.ClientRepository;
import com.wanderalvess.clientsback.repository.PhoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private PhoneRepository phoneRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    public void testList() {
        List<Client> clients = new ArrayList<>();

        List<Phone> phones1 = getPhones("(62) 99898-6969");

        Client client1 = new Client();
        client1.setId(1L);
        client1.setName("João Ferreira");
        client1.setDocument("48690209042");
        client1.setAddress("Rua 1");
        client1.setNeighborhood("Serrinha");
        client1.setLatitude("10.12345");
        client1.setLongitude("10.12345");
        client1.setPhones(phones1);

        clients.add(client1);

        List<Phone> phones2 = getPhones("(62) 99898-6969");

        Client client2 = new Client();
        client2.setId(2L);
        client2.setName("Maria Ferreira");
        client2.setDocument("77485980025");
        client2.setAddress("Rua 2");
        client2.setNeighborhood("Bairro 2");
        client2.setLatitude("20.12345");
        client2.setLongitude("20.12345");
        client2.setPhones(phones2);

        clients.add(client2);

        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = clientController.list();

        assertNotNull(result);
        assertEquals(2, result.size());

        // Verificar os detalhes do primeiro cliente
        assertEquals("João Ferreira", result.get(0).getName());
        assertEquals("48690209042", result.get(0).getDocument());
        assertEquals(1, result.get(0).getPhones().size());
        assertEquals("(62) 99898-6969", result.get(0).getPhones().get(0).getNumber());

        // Verificar os detalhes do segundo cliente
        assertEquals("Maria Ferreira", result.get(1).getName());
        assertEquals("77485980025", result.get(1).getDocument());
        assertEquals(1, result.get(1).getPhones().size());
        assertEquals("(62) 99898-6969", result.get(1).getPhones().get(0).getNumber());

        verify(clientRepository, times(1)).findAll();
    }



    @Test
    public void testFindById() {

        List<Phone> phones = getPhones("(62) 99898-6969");

        Long clientId = 1L;
        Client client = new Client(clientId, "João Ferreira", "48690209042", "Rua 1","Serrinha" , "10.12345","20.67890",phones);
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
        Client client = new Client();
        List<Phone> phones = getPhones("(62) 99898-6969");
        client.setName("João Ferreira");
        client.setDocument("48690209042");
        client.setAddress("Rua 1");
        client.setNeighborhood("Jardim Goiás");
        client.setLatitude("10.12345");
        client.setLongitude("20.67890");
        client.setPhones(phones);
        client.setId(1L);

        when(clientRepository.save(any(Client.class))).thenReturn(client);

        String requestJson = "{\"name\":\"João Ferreira\"," +
                "\"document\":\"48690209042\"," +
                "\"address\":\"Rua 1\"," +
                "\"neighborhood\":\"Jardim Goiás\"," +
                "\"latitude\":\"10.12345\"," +
                "\"longitude\":\"20.67890\"," +
                "\"phones\":[{\"number\":\"(62) 99898-6969\", \"_id\":\"1\"}]," +
                "\"_id\":\"1\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);

        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("João Ferreira"))
                .andDo(MockMvcResultHandlers.print());

        MvcResult mvcResult = resultActions.andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();

        verify(clientRepository, times(1)).save(any(Client.class));
    }

    private static List<Phone> getPhones(String number) {
        Phone phone = new Phone();
        phone.setNumber(number);
        phone.setId(1L);

        List<Phone> phones = new ArrayList<>();
        phones.add(phone);
        return phones;
    }
}