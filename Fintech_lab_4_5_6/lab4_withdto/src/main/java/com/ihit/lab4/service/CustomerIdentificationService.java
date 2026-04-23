package com.ihit.lab4.service;

import com.ihit.lab4.dto.CustomerIdentificationDTO;

public interface CustomerIdentificationService {
    CustomerIdentificationDTO create(CustomerIdentificationDTO dto);
    CustomerIdentificationDTO getById(Long id);
    CustomerIdentificationDTO update(Long id, CustomerIdentificationDTO dto);
    void delete(Long id);
}
