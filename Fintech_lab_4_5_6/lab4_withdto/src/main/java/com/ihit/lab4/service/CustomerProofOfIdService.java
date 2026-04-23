package com.ihit.lab4.service;
import com.ihit.lab4.dto.CustomerProofOfIdDTO;

public interface CustomerProofOfIdService {
    CustomerProofOfIdDTO create(CustomerProofOfIdDTO dto);
    CustomerProofOfIdDTO getById(Long id);
    CustomerProofOfIdDTO update(Long id, CustomerProofOfIdDTO dto);
    void delete(Long id);
}
