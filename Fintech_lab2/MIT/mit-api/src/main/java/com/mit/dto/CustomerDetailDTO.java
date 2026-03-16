package com.mit.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.mit.entity.CustomerDetails;
import com.mit.entity.CustomerName;

public class CustomerDetailDTO {
	private Long id;
	private Long customerIdentifier;
	private String customerType;
	private String customerFullName;
	private Date customerDOB;
	private String customerStatus;
	private String customerGender;
	private String customerPreferredLanguage;
	private String customerCountryOrigin;
	private Date effectiveDate;
	private char CRUDValue;
	private String userID;
	private String workstationID;
	private String ProgramID;
	private Timestamp hostTimestamp;
	private Timestamp localTimestamp;
	private Timestamp acceptanceTimestamp;
	private Timestamp acceptanceTimestampUTCoffset;
	private String uuid;
	private List<CustomerName> customerNames;
	
	public Long getCustomerIdentifier() {
		return customerIdentifier;
	}
	public void setCustomerIdentifier(Long customerIdentifier) {
		this.customerIdentifier = customerIdentifier;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCustomerFullName() {
		return customerFullName;
	}
	public void setCustomerFullName(String customerFullName) {
		this.customerFullName = customerFullName;
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
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public char getCRUDValue() {
		return CRUDValue;
	}
	public void setCRUDValue(char cRUDValue) {
		CRUDValue = cRUDValue;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getWorkstationID() {
		return workstationID;
	}
	public void setWorkstationID(String workstationID) {
		this.workstationID = workstationID;
	}
	public String getProgramID() {
		return ProgramID;
	}
	public void setProgramID(String programID) {
		ProgramID = programID;
	}
	public Timestamp getHostTimestamp() {
		return hostTimestamp;
	}
	public void setHostTimestamp(Timestamp hostTimestamp) {
		this.hostTimestamp = hostTimestamp;
	}
	public Timestamp getLocalTimestamp() {
		return localTimestamp;
	}
	public void setLocalTimestamp(Timestamp localTimestamp) {
		this.localTimestamp = localTimestamp;
	}
	public Timestamp getAcceptanceTimestamp() {
		return acceptanceTimestamp;
	}
	public void setAcceptanceTimestamp(Timestamp acceptanceTimestamp) {
		this.acceptanceTimestamp = acceptanceTimestamp;
	}
	public Timestamp getAcceptanceTimestampUTCoffset() {
		return acceptanceTimestampUTCoffset;
	}
	public void setAcceptanceTimestampUTCoffset(Timestamp acceptanceTimestampUTCoffset) {
		this.acceptanceTimestampUTCoffset = acceptanceTimestampUTCoffset;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public List<CustomerName> getCustomerNames() {
		return customerNames;
	}
	public void setCustomerNames(List<CustomerName> customerNames) {
		this.customerNames = customerNames;
	}
	@Override
	public CustomerDetails clone()  {
		CustomerDetails customerDetails=new CustomerDetails();
		customerDetails.setAcceptanceTimestamp(acceptanceTimestamp);
		customerDetails.setAcceptanceTimestampUTCoffset(acceptanceTimestampUTCoffset);
		customerDetails.setCRUDValue(CRUDValue);
		customerDetails.setCustomerCountryOrigin(customerCountryOrigin);
		customerDetails.setCustomerDOB(customerDOB);
		customerDetails.setCustomerGender(customerGender);
		customerDetails.setCustomerIdentifier(customerIdentifier);
		customerDetails.setCustomerPreferredLanguage(customerPreferredLanguage);
		customerDetails.setCustomerStatus(customerStatus);
		customerDetails.setCustomerType(customerType);
		customerDetails.setEffectiveDate(effectiveDate);
		customerDetails.setHostTimestamp(hostTimestamp);
		customerDetails.setLocalTimestamp(localTimestamp);
		customerDetails.setProgramID(ProgramID);
		customerDetails.setUserID(userID);
		customerDetails.setUuid(uuid);
		customerDetails.setWorkstationID(workstationID);
		return customerDetails;
	}

}
