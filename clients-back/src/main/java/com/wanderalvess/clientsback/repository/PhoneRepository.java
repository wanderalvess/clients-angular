package com.wanderalvess.clientsback.repository;

import com.wanderalvess.clientsback.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    boolean existsByNumber(String number);
}
