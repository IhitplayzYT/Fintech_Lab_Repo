package com.mit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mit.dto.CustomerDetailsInputDTO;
import com.mit.dto.CustomerDetailsResponseDTO;
import com.mit.dto.UserLoginDTO;
import com.mit.entity.CustomerDetails;
import com.mit.response.BasicResponse;
import com.mit.response.StandardResponse;
import com.mit.service.CustomerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(maxAge = 3600, origins = "*")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/addCustomerDetails")
	@ApiOperation(value = "This API is used to Add Customer Details ")
	public StandardResponse<CustomerDetails> addCustomerDetails(
			@RequestBody CustomerDetailsInputDTO customerDetailsInputDTO) {
		StandardResponse<CustomerDetails> response = new StandardResponse<CustomerDetails>();
		response.setResponseOK();
		CustomerDetails customerDetails = customerService.addCustomerDetails(customerDetailsInputDTO);
		response.setData(customerDetails);
		return response;

	}

	@ApiOperation(value = "This API is used to retrieve Customers for gived id")
	@GetMapping("/getCustomerDetails/{id}")
	public StandardResponse<CustomerDetailsResponseDTO> getCustomerDetails(@PathVariable Long id) {
		StandardResponse<CustomerDetailsResponseDTO> response = new StandardResponse<CustomerDetailsResponseDTO>();
		response.setResponseOK();
		response.setData(customerService.getCustomerDetails(id));
		return response;

	}

	@PutMapping("/updateCustomerDetails/{id}")
	@ApiOperation(value = "This API is used to Update Clasification Type")
	public BasicResponse updateCustomerDetails(@PathVariable Long id,
			@RequestBody CustomerDetailsInputDTO customerDetailsUpdateDTO) {
		BasicResponse response = new BasicResponse();
		response.setResponseOK();
		customerService.updateCustomerDetails(id, customerDetailsUpdateDTO);
		return response;

	}

	@ApiOperation(value = "This API is used to delete Clasification Type")
	@DeleteMapping("/deleteCustomerDetails/{id}")
	@CrossOrigin
	public BasicResponse deleteCustomerDetails(@PathVariable Long id) {
		BasicResponse response = new BasicResponse();
		response.setResponseOK();
		customerService.deleteCustomerDetails(id);
		return response;

	}

	@PostMapping("/userLogin")
	@ApiOperation(value = "This API is used to Add Customer Details ")
	public StandardResponse<CustomerDetails> addCustomerDetails(@RequestBody UserLoginDTO userLoginDTO) {
		StandardResponse<CustomerDetails> response = new StandardResponse<CustomerDetails>();
		response.setResponseOK();
		CustomerDetails customerDetails = customerService.getUserLoginResponse(userLoginDTO);
		response.setData(customerDetails);
		return response;

	}

	@ApiOperation(value = "This API is used to retrieve Customers for gived id")
	@GetMapping("/getCustomers")
	public StandardResponse<List<CustomerDetailsResponseDTO>> getCustomers(@RequestParam("custType") String custType) {
		StandardResponse<List<CustomerDetailsResponseDTO>> response = new StandardResponse<List<CustomerDetailsResponseDTO>>();
		response.setResponseOK();
		response.setData(customerService.getCustomers(custType));
		return response;

	}

	@ApiOperation(value = "This API is used to retrieve Customers for gived id")
	@GetMapping("/getAllHistory")
	public StandardResponse<List<CustomerDetailsResponseDTO>> getAllHistory(@RequestParam("custType") String custType) {
		StandardResponse<List<CustomerDetailsResponseDTO>> response = new StandardResponse<List<CustomerDetailsResponseDTO>>();
		response.setResponseOK();
		response.setData(customerService.getAllHistory(custType));
		return response;

	}

	@ApiOperation(value = "This api is ude to validate the login credentials")
	@PostMapping("/login")
	public BasicResponse checkLogin(@RequestBody UserLoginDTO requestBody) {
		BasicResponse response = new BasicResponse();
		if (requestBody.getusername() != null && requestBody.getpassword() != null
				&& requestBody.getusername().equalsIgnoreCase("Admin") && requestBody.getpassword().equals("Admin")) {
			response.setResponseOK();
		} else {
			response.setResponse(null, "Invalid credentials", null);
		}

		return response;
	}
}
