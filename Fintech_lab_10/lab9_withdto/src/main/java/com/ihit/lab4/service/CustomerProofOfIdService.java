package com.ihit.lab4.service;
import org.springframework.data.domain.Page;

import com.ihit.lab4.dto.CustomerProofOfIdDTO;
public interface CustomerProofOfIdService {
    CustomerProofOfIdDTO create(CustomerProofOfIdDTO dto);
    CustomerProofOfIdDTO getById(Long id);
    CustomerProofOfIdDTO update(Long id, CustomerProofOfIdDTO dto);
    void delete(Long id);
    public Page<CustomerProofOfIdDTO> getPage(int page,int page_sz);
    public Page<CustomerProofOfIdDTO> findPage(int page,int page_sz);
}
