package com.mit.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "CUST_NME_CMPNT")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerName {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "CSTID_IDFR")
	private Long customerIdentifier;

	@Column(name = "CSTNMCMP_TYPE_ID")
	private String customerNameComponentType;
	@Column(name = "CSTNMCMP_TYPE_VALUE")
	private String customerNameComponentValue;
	@Column(name = "CSTNMCMP_EFCTV_DT")
	private Date effectiveDate;
	@Column(name = "CSTNMCMP_CRUD_VALUE")
	private char CRUDValue;
	@Column(name = "CSTNMCMP_USER_ID")
	private String userID;
	@Column(name = "CSTNMCMP_WS_ID")
	private String workstationID;
	@Column(name = "CSTNMCMP_PRGM_ID")
	private String ProgramID;
	@Column(name = "CSTNMCMP_HOST_TS")
	private Timestamp hostTimestamp;
	@Column(name = "CSTNMCMP_LOCAL_TS")
	private Timestamp localTimestamp;
	@Column(name = "CSTNMCMP_ACPT_TS")
	private Timestamp acceptanceTimestamp;
	@Column(name = "CSTNMCMP_ACPT_TS_UTC_OFST")
	private Timestamp acceptanceTimestampUTCoffset;
	@Column(name = "CSTNMCMP_UUID")
	private String uuid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getCustomerIdentifier() {
		return customerIdentifier;
	}

	public void setCustomerIdentifier(Long customerIdentifier) {
		this.customerIdentifier = customerIdentifier;
	}
	

}
