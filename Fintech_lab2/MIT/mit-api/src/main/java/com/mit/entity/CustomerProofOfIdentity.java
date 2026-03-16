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
@Table(name = "CUST_PRIDN")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerProofOfIdentity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "CSTID_IDFR", nullable = false)
	@JsonIgnore
	private CustomerDetails customerIdentification;

	@OneToOne
	@JoinColumn(name = "CSTCL_ID", nullable = false)
	@JsonIgnore
	private CustomerClassification customerClassificationID;

	@Column(name = "CSTPRID_VALUE")
	private String customerClassificationTypeValue;
	@Column(name = "CSTPRID_STRT_DT")
	private Date startDate;
	@Column(name = "CSTPRID_END_DT")
	private Date endDate;
	@Column(name = "CSTPRID_CRUD_VALUE")
	private char CRUDValue;
	@Column(name = "CSTPRID_USER_ID")
	private String userID;
	@Column(name = "CSTPRID_WS_ID")
	private String workstationID;
	@Column(name = "CSTPRID_PRGM_ID")
	private String ProgramID;
	@Column(name = "CSTPRID_HOST_TS")
	private Timestamp hostTimestamp;
	@Column(name = "CSTPRID_LOCAL_TS")
	private Timestamp localTimestamp;
	@Column(name = "CSTPRID_ACPT_TS")
	private Timestamp acceptanceTimestamp;
	@Column(name = "CSTPRID_ACPT_TS_UTC_OFST")
	private Timestamp acceptanceTimestampUTCoffset;
	@Column(name = "CSTPRID_UUID")
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

	public CustomerClassification getCustomerClassificationID() {
		return customerClassificationID;
	}

	public void setCustomerClassificationID(CustomerClassification customerClassificationID) {
		this.customerClassificationID = customerClassificationID;
	}

	public String getCustomerClassificationTypeValue() {
		return customerClassificationTypeValue;
	}

	public void setCustomerClassificationTypeValue(String customerClassificationTypeValue) {
		this.customerClassificationTypeValue = customerClassificationTypeValue;
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
