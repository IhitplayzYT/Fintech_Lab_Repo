package com.ihit.lab4.service;

import com.ihit.lab4.dto.CustomerDetailDTO;

import org.springframework.data.domain.Page;
public interface CustomerDetailService {
    CustomerDetailDTO create(CustomerDetailDTO dto);
    CustomerDetailDTO getById(Long id);
    CustomerDetailDTO update(Long id, CustomerDetailDTO dto);
    void delete(Long id);
    public Page<CustomerDetailDTO> getPage(int page,int page_sz);
    public Page<CustomerDetailDTO> findPage(int page,int page_sz);
}
