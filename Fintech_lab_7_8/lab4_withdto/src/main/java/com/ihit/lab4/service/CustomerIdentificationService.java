package com.ihit.lab4.service;

import com.ihit.lab4.dto.CustomerIdentificationDTO;

import org.springframework.data.domain.Page;
public interface CustomerIdentificationService {
    CustomerIdentificationDTO create(CustomerIdentificationDTO dto);
    CustomerIdentificationDTO getById(Long id);
    CustomerIdentificationDTO update(Long id, CustomerIdentificationDTO dto);
    void delete(Long id);
    public Page<CustomerIdentificationDTO> getPage(int page,int page_sz);
    public Page<CustomerIdentificationDTO> findPage(int page,int page_sz);
}
