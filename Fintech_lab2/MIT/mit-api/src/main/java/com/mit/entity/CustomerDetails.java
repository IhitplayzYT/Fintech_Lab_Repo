package com.mit.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "CUST_DTL")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDetails implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CST_IDFR")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSeqGen")
    @SequenceGenerator(name = "customerid_seq", sequenceName = "customer_id_seq",initialValue = 100000 , allocationSize = 1)
	@Column(name = "CSTID_IDFR")
	private Long customerIdentifier;
	@Column(name = "CUST_TYPE")
	private String customerType;

	@Column(name = "CUST_DOB")
	private Date customerDOB;
	@Column(name = "CUST_STATUS")
	private String customerStatus;
	@Column(name = "CUST_GENDR")
	private String customerGender;
	@Column(name = "CUST_PRFRD_LNG")
	private String customerPreferredLanguage;
	@Column(name = "CUST_CNTRY_ORGTN")
	private String customerCountryOrigin;
	@Column(name = "CUST_EFCTV_DT")
	private Date effectiveDate;
	@Column(name = "CUST_CRUD_VALUE")
	private char CRUDValue;
	@Column(name = "CUST_USER_ID")
	private String userID;
	@Column(name = "CUST_WS_ID")
	private String workstationID;
	@Column(name = "CUST_PRGM_ID")
	private String ProgramID;
	@Column(name = "CUST_HOST_TS")
	private Timestamp hostTimestamp;
	@Column(name = "CUST_LOCAL_TS")
	private Timestamp localTimestamp;
	@Column(name = "CUST_ACPT_TS")
	private Timestamp acceptanceTimestamp;
	@Column(name = "CUST_ACPT_TS_UTC_OFST")
	private Timestamp acceptanceTimestampUTCoffset;
	@Column(name = "CUST_UUID")
	private String uuid;
	
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
