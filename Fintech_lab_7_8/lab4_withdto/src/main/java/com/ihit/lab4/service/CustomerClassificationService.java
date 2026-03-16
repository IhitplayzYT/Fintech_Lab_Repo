package com.ihit.lab4.service;
import com.ihit.lab4.dto.CustomerClassificationDTO;

import org.springframework.data.domain.Page;
public interface CustomerClassificationService {
    CustomerClassificationDTO create(CustomerClassificationDTO dto);
    CustomerClassificationDTO getById(Long id);
    CustomerClassificationDTO update(Long id, CustomerClassificationDTO dto);
    void delete(Long id);
    public Page<CustomerClassificationDTO> getPage(int page,int page_sz);
    public Page<CustomerClassificationDTO> findPage(int page,int page_sz);
}
