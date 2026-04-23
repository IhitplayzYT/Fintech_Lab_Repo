package com.ihit.lab4.service;
import com.ihit.lab4.dto.CustomerNameDTO;

public interface CustomerNameService {
    CustomerNameDTO create(CustomerNameDTO dto);
    CustomerNameDTO getById(Long id);
    CustomerNameDTO update(Long id, CustomerNameDTO dto);
    void delete(Long id);
}
