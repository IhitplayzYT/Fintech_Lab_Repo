package com.ihit.lab4.service;
import com.ihit.lab4.dto.CustomerContactInformationDTO;

public interface CustomerContactInformationService {
    CustomerContactInformationDTO create(CustomerContactInformationDTO dto);
    CustomerContactInformationDTO getById(Long id);
    CustomerContactInformationDTO update(Long id, CustomerContactInformationDTO dto);
    void delete(Long id);
}
