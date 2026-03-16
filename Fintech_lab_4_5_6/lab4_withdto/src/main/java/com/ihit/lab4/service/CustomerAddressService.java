package com.ihit.lab4.service;
import com.ihit.lab4.dto.CustomerAddressDTO;

public interface CustomerAddressService {
    CustomerAddressDTO create(CustomerAddressDTO dto);
    CustomerAddressDTO getById(Long id);
    CustomerAddressDTO update(Long id, CustomerAddressDTO dto);
    void delete(Long id);
}
