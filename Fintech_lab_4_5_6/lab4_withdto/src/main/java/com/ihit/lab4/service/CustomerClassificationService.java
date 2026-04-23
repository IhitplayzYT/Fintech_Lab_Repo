package com.ihit.lab4.service;
import com.ihit.lab4.dto.CustomerClassificationDTO;

public interface CustomerClassificationService {
    CustomerClassificationDTO create(CustomerClassificationDTO dto);
    CustomerClassificationDTO getById(Long id);
    CustomerClassificationDTO update(Long id, CustomerClassificationDTO dto);
    void delete(Long id);
}
