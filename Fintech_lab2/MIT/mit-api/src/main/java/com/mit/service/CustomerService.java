package com.mit.service;

import java.util.List;

import com.mit.dto.CustomerDetailsInputDTO;
import com.mit.dto.CustomerDetailsResponseDTO;
import com.mit.dto.UserLoginDTO;
import com.mit.entity.CustomerDetails;

public interface CustomerService {

	
	CustomerDetails addCustomerDetails(CustomerDetailsInputDTO customerDetailsInputDTO);
	
	CustomerDetailsResponseDTO getCustomerDetails(Long id);
	
	void deleteCustomerDetails(Long id);

	void updateCustomerDetails(Long id, CustomerDetailsInputDTO customerDetailsUpdateDTO);

	CustomerDetails getUserLoginResponse(UserLoginDTO userLoginDTO);
	
	List<CustomerDetailsResponseDTO> getCustomers(String custType);

	List<CustomerDetailsResponseDTO> getAllHistory(String custType);

}
