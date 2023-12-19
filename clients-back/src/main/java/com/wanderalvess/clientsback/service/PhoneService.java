package com.wanderalvess.clientsback.service;

import com.wanderalvess.clientsback.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneService {
    PhoneRepository phoneRepository;

    @Autowired
    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public boolean existsByNumber(String phone) {
        return phoneRepository.existsByNumber(phone);
    }

}
