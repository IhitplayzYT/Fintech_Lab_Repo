package com.ihit.lab4.service;

import com.ihit.lab4.dto.CustomerDetailDTO;

public interface CustomerDetailService {
    CustomerDetailDTO create(CustomerDetailDTO dto);
    CustomerDetailDTO getById(Long id);
    CustomerDetailDTO update(Long id, CustomerDetailDTO dto);
    void delete(Long id);
}
