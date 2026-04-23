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
@Table(name = "CUST_CNTCT_DTLS")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerContactDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CSTCNDT_ID")
	private Long customerContactID;

	@OneToOne
	@JoinColumn(name = "CSTID_IDFR", nullable = false)
	@JsonIgnore
	private CustomerDetails customerIdentification;

	@Column(name = "CSTCNDT_VALUE")
	private String customerContactDetails;
	@Column(name = "CSTCNDT_STRT_DT")
	private Date startDate;
	@Column(name = "CSTCNDT_END_DT")
	private Date endDate;
	@Column(name = "CSTCNDT_CRUD_VALUE")
	private char CRUDValue;
	@Column(name = "CSTCNDT_USER_ID")
	private String userID;
	@Column(name = "CSTCNDT_WS_ID")
	private String workstationID;
	@Column(name = "CSTCNDT_PRGM_ID")
	private String ProgramID;
	@Column(name = "CSTCNDT_HOST_TS")
	private Timestamp hostTimestamp;
	@Column(name = "CSTCNDT_LOCAL_TS")
	private Timestamp localTimestamp;
	@Column(name = "CSTCNDT_ACPT_TS")
	private Timestamp acceptanceTimestamp;
	@Column(name = "CSTCNDT_ACPT_TS_UTC_OFST")
	private Timestamp acceptanceTimestampUTCoffset;
	@Column(name = "CSTCNDT_UUID")
	private String uuid;

	public Long getCustomerContactID() {
		return customerContactID;
	}

	public void setCustomerContactID(Long customerContactID) {
		this.customerContactID = customerContactID;
	}

	public CustomerDetails getCustomerIdentification() {
		return customerIdentification;
	}

	public void setCustomerIdentification(CustomerDetails customerIdentification) {
		this.customerIdentification = customerIdentification;
	}

	public String getCustomerContactDetails() {
		return customerContactDetails;
	}

	public void setCustomerContactDetails(String customerContactDetails) {
		this.customerContactDetails = customerContactDetails;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
