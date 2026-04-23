package com.ihit.lab4.service;
import org.springframework.data.domain.Page;

import com.ihit.lab4.dto.CustomerNameDTO;
public interface CustomerNameService {
    CustomerNameDTO create(CustomerNameDTO dto);
    CustomerNameDTO getById(Long id);
    CustomerNameDTO update(Long id, CustomerNameDTO dto);
    void delete(Long id);
    public Page<CustomerNameDTO> getPage(int page,int page_sz);
    public Page<CustomerNameDTO> findPage(int page,int page_sz);
}
