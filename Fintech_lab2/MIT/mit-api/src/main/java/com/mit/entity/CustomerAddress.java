package com.mit.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "CUST_ADDR_CMPNT")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "CSTID_IDFR", nullable = false)
	@JsonIgnore
	private CustomerDetails customerIdentification;

	@Column(name = "CSTADCMP_TYPE_ID")
	private String customerNameComponentType;
	@Column(name = "CSTADCMP_TYPE_VALUE")
	private String customerNameComponentValue;
	@Column(name = "CSTADCMP_EFCTV_DT")
	private Date effectiveDate;
	@Column(name = "CSTADCMP_CRUD_VALUE")
	private char CRUDValue;
	@Column(name = "CSTADCMP_USER_ID")
	private String userID;
	@Column(name = "CSTADCMP_WS_ID")
	private String workstationID;
	@Column(name = "CSTADCMP_PRGM_ID")
	private String ProgramID;
	@Column(name = "CSTADCMP_HOST_TS")
	private Timestamp hostTimestamp;
	@Column(name = "CSTADCMP_LOCAL_TS")
	private Timestamp localTimestamp;
	@Column(name = "CSTADCMP_ACPT_TS")
	private Timestamp acceptanceTimestamp;
	@Column(name = "CSTADCMP_ACPT_TS_UTC_OFST")
	private Timestamp acceptanceTimestampUTCoffset;
	@Column(name = "CSTADCMP_UUID")
	private String uuid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomerDetails getCustomerIdentification() {
		return customerIdentification;
	}

	public void setCustomerIdentification(CustomerDetails customerIdentification) {
		this.customerIdentification = customerIdentification;
	}

	public String getCustomerNameComponentType() {
		return customerNameComponentType;
	}

	public void setCustomerNameComponentType(String customerNameComponentType) {
		this.customerNameComponentType = customerNameComponentType;
	}

	public String getCustomerNameComponentValue() {
		return customerNameComponentValue;
	}

	public void setCustomerNameComponentValue(String customerNameComponentValue) {
		this.customerNameComponentValue = customerNameComponentValue;
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

}
