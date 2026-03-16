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
@Table(name = "CUST_CL")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerClassification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CSTCL_ID")
	private Long customerClassificationID;
	@Column(name = "CSTCL_TYP")
	private String customerClassificationType;
	@Column(name = "CSTCL_TYP_VALUE")
	private String CustomerClassificationTypeValue;
	@Column(name = "CSTCL_EFCTV_DT")
	private Date effectiveDate;
	@Column(name = "CSTCL_CRUD_VALUE")
	private char CRUDValue;
	@Column(name = "CSTCL_USER_ID")
	private String userID;
	@Column(name = "CSTCL_WS_ID")
	private String workstationID;
	@Column(name = "CSTCL_PRGM_ID")
	private String ProgramID;
	@Column(name = "CSTCL_HOST_TS")
	private Timestamp hostTimestamp;
	@Column(name = "CSTCL_LOCAL_TS")
	private Timestamp localTimestamp;
	@Column(name = "CSTCL_ACPT_TS")
	private Timestamp acceptanceTimestamp;
	@Column(name = "CSTCL_ACPT_TS_UTC_OFST")
	private Timestamp acceptanceTimestampUTCoffset;
	@Column(name = "CSTCL_UUID")
	private String uuid;


	public Long getCustomerClassificationID() {
		return customerClassificationID;
	}

	public void setCustomerClassificationID(Long customerClassificationID) {
		this.customerClassificationID = customerClassificationID;
	}

	public String getCustomerClassificationType() {
		return customerClassificationType;
	}

	public void setCustomerClassificationType(String customerClassificationType) {
		this.customerClassificationType = customerClassificationType;
	}

	public String getCustomerClassificationTypeValue() {
		return CustomerClassificationTypeValue;
	}

	public void setCustomerClassificationTypeValue(String customerClassificationTypeValue) {
		CustomerClassificationTypeValue = customerClassificationTypeValue;
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
