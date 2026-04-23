package com.ihit.lab4.service;
import com.ihit.lab4.dto.CustomerContactInformationDTO;

import org.springframework.data.domain.Page;
public interface CustomerContactInformationService {
    CustomerContactInformationDTO create(CustomerContactInformationDTO dto);
    CustomerContactInformationDTO getById(Long id);
    CustomerContactInformationDTO update(Long id, CustomerContactInformationDTO dto);
    void delete(Long id);
    public Page<CustomerContactInformationDTO> getPage(int page,int page_sz);
    public Page<CustomerContactInformationDTO> findPage(int page,int page_sz);
}
