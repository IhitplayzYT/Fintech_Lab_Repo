package com.mit.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "CUST_IDFN")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerIdentification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CSTIN_IDFR")
	private Long customerIdentificationID;
	@Column(name = "CSTIN_ID_TYPE")
	private String customerIdentificationType;
	@Column(name = "CSTIN_ID_ITEM")
	private String customerIdentificationItem;
	@Column(name = "CSTIN_EFCTV_DT")
	private Date effectiveDate;
	@Column(name = "CSTIN_CRUD_VALUE")
	private char CRUDValue;
	@Column(name = "CSTIN_USER_ID")
	private String userID;
	@Column(name = "CSTIN_WS_ID")
	private String workstationID;
	@Column(name = "CSTIN_PRGM_ID")
	private String ProgramID;
	@Column(name = "CSTIN_HOST_TS")
	private Timestamp hostTimestamp;
	@Column(name = "CSTIN_LOCAL_TS")
	private Timestamp localTimestamp;
	@Column(name = "CSTIN_ACPT_TS")
	private Timestamp acceptanceTimestamp;
	@Column(name = "CSTIN_ACPT_TS_UTC_OFST")
	private Timestamp acceptanceTimestampUTCoffset;
	@Column(name = "CSTIN_UUID")
	private String uuid;

	public Long getCustomerIdentificationID() {
		return customerIdentificationID;
	}

	public void setCustomerIdentificationID(Long customerIdentificationID) {
		this.customerIdentificationID = customerIdentificationID;
	}

	public String getCustomerIdentificationType() {
		return customerIdentificationType;
	}

	public void setCustomerIdentificationType(String customerIdentificationType) {
		this.customerIdentificationType = customerIdentificationType;
	}

	public String getCustomerIdentificationItem() {
		return customerIdentificationItem;
	}

	public void setCustomerIdentificationItem(String customerIdentificationItem) {
		this.customerIdentificationItem = customerIdentificationItem;
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
