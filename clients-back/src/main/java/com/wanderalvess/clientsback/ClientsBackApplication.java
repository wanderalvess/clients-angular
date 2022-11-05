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

			c.setCode("1");
			c.setName("João");
			c.setDocument("11.111.111/0001-11");
			c.setLatitude("11.111.111");
			c.setLongitude("11.111.111");

			clientRepository.save(c);

		};
	}

	}
