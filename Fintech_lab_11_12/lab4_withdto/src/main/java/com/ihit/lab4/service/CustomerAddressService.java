package com.ihit.lab4.service;
import com.ihit.lab4.dto.CustomerAddressDTO;
import org.springframework.data.domain.Page;

public interface CustomerAddressService {
    CustomerAddressDTO create(CustomerAddressDTO dto);
    CustomerAddressDTO getById(Long id);
    CustomerAddressDTO update(Long id, CustomerAddressDTO dto);
    public Page<CustomerAddressDTO> getPage(int page,int page_sz);
    public Page<CustomerAddressDTO> findPage(int page,int page_sz);
    void delete(Long id);
}
