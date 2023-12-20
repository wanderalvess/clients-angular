package com.wanderalvess.clientsback.repository;

import com.wanderalvess.clientsback.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByDocument(String document);
}
