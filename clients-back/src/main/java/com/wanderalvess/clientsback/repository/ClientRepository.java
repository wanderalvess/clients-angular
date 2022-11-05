package com.wanderalvess.clientsback.repository;

import com.wanderalvess.clientsback.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
