package com.wanderalvess.clientsback;

import com.wanderalvess.clientsback.model.Client;
import com.wanderalvess.clientsback.model.Phone;
import com.wanderalvess.clientsback.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
public class ClientsBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientsBackApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ClientRepository clientRepository) {
		return args -> {
			clientRepository.deleteAll();

			// Criar clientes e telefones de exemplo
			Client client1 = createClient("Exemplo Cliente ", "11.111.111/0001-11",
					"Av. Dep. Jamel Cecílio", "Jardim Goiás", "(62)99898-6969",
					 "-16.70435700806895", "-49.24028957783302");
			Client client2 = createClient("Exemplo Empresa", "222.222.222-22", "Rua Exemplo, 123",
					"Bairro Exemplo", "(62)93333-6969",
					 "-16.70500000000000", "-49.24100000000000");

			clientRepository.saveAll(Arrays.asList(client1, client2));
		};
	}

	private Client createClient(String name, String document, String address, String neighborhood, String phoneNumber, String latitude, String longitude) {
		Client client = new Client();
		client.setName(name);
		client.setDocument(document);
		client.setAddress(address);
		client.setNeighborhood(neighborhood);
		client.setLatitude(latitude);
		client.setLongitude(longitude);

		Phone phone = new Phone();
		phone.setNumber(phoneNumber);
		phone.setClient(client);

		List<Phone> phones = new ArrayList<>();
		phones.add(phone);

		client.setPhones(phones);
		return client;
	}
}