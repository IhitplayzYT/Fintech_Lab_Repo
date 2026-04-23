package com.mit.dto;

import java.sql.Date;
import java.util.List;

public class CustomerDetailsResponseDTO {
	
	private Long customerid;

	private String customerType;
	
	private List<TypeValue> customerName;
	
	private Date customerDOB;
	
	private String customerStatus;
	
	private String customerGender;
	
	private String customerPreferredLanguage;
	
	
	
	public Long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public List<TypeValue> getCustomerName() {
		return customerName;
	}

	public void setCustomerName(List<TypeValue> customerName) {
		this.customerName = customerName;
	}

	public Date getCustomerDOB() {
		return customerDOB;
	}

	public void setCustomerDOB(Date customerDOB) {
		this.customerDOB = customerDOB;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getCustomerGender() {
		return customerGender;
	}

	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}

	public String getCustomerPreferredLanguage() {
		return customerPreferredLanguage;
	}

	public void setCustomerPreferredLanguage(String customerPreferredLanguage) {
		this.customerPreferredLanguage = customerPreferredLanguage;
	}

	public String getCustomerCountryOrigin() {
		return customerCountryOrigin;
	}

	public void setCustomerCountryOrigin(String customerCountryOrigin) {
		this.customerCountryOrigin = customerCountryOrigin;
	}

	private String customerCountryOrigin;
}
