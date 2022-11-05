package com.wanderalvess.clientsback;

import com.wanderalvess.clientsback.model.Client;
import com.wanderalvess.clientsback.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientsBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientsBackApplication.class, args);
	}


	@Bean
	CommandLineRunner initDatabase(ClientRepository clientRepository) {
		return args -> {
			clientRepository.deleteAll();

			Client c = new Client();

			c.setName("João");
			c.setDocument("11.111.111/0001-11");
			c.setAddress("Av. 136");
			c.setLatitude("-16.700884642335396");
			c.setLongitude("-49.25342831243458");

			clientRepository.save(c);

		};
	}

	}
