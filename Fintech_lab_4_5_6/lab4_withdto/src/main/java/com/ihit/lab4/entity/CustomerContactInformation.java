package com.ihit.lab4.entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Entity
@Table(name = "customer_contact_information")
public class CustomerContactInformation {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long customer_identifier;

private String customer_contact_type;
private String customer_contact_value;
private Date effective_date;
private Date start_date;
private Date end_date;

// ===== AUDIT FIELDS =====
@Column(name = "crud_flag", length = 1)
private String crud_flag;
@Column(name = "audit_uuid", length = 100, updatable = false)
private String audit_uuid;
@Column(name = "audit_uid", length = 100)
private String audit_uid;
@Column(name = "audit_ws_id", length = 100)
private String audit_ws_id;
@Column(name = "audit_pid")
private Long audit_pid;
@Column(name = "audit_host_time")
private OffsetDateTime audit_host_time;
@Column(name = "audit_local_time")
private OffsetDateTime audit_local_time;
@Column(name = "audit_acceptance_time")
private OffsetDateTime audit_acceptance_time;
@Column(name = "audit_acceptance_time_utc")
private OffsetDateTime audit_acceptance_time_utc;

@ManyToOne
@JoinColumn(name = "customer_contact_detail_FK")
private CustomerDetail customer;

public CustomerContactInformation(){}

@PreUpdate
public void preUpdate() {

    this.audit_acceptance_time = OffsetDateTime.now(ZoneOffset.UTC);
}

@PreRemove
public void preDelete() {

    this.audit_acceptance_time = OffsetDateTime.now(ZoneOffset.UTC);
}

    @PrePersist
    public void prePersist() {

        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);

        if (this.crud_flag == null)
            this.crud_flag = "C";

        if (this.audit_uuid == null)
            this.audit_uuid = UUID.randomUUID().toString();

        if (this.audit_host_time == null)
            this.audit_host_time = now;

        if (this.audit_local_time == null)
            this.audit_local_time = now;

        if (this.audit_acceptance_time == null)
            this.audit_acceptance_time = now;

        if (this.audit_acceptance_time_utc == null)
            this.audit_acceptance_time_utc = now;

        if (this.audit_pid == null)
            this.audit_pid = 0L;   // temporary until version root is set
    }



public Long getCustomer_identifier() {
    return customer_identifier;
}

public void setCustomer_identifier(Long customer_identifier) {
    this.customer_identifier = customer_identifier;
}

public String getCustomer_contact_type() {
    return customer_contact_type;
}

public void setCustomer_contact_type(String customer_contact_type) {
    this.customer_contact_type = customer_contact_type;
}

public String getCustomer_contact_value() {
    return customer_contact_value;
}

public void setCustomer_contact_value(String customer_contact_value) {
    this.customer_contact_value = customer_contact_value;
}

public Date getEffective_date() {
    return effective_date;
}

public void setEffective_date(Date effective_date) {
    this.effective_date = effective_date;
}

public Date getStart_date() {
    return start_date;
}

public void setStart_date(Date start_date) {
    this.start_date = start_date;
}

public Date getEnd_date() {
    return end_date;
}

public void setEnd_date(Date end_date) {
    this.end_date = end_date;
}

public CustomerDetail getCustomer() {
    return customer;
}

public void setCustomer(CustomerDetail customer) {
    this.customer = customer;
}

public String getCrud_flag() {
    return crud_flag;
}

public void setCrud_flag(String crud_flag) {
    this.crud_flag = crud_flag;
}

public String getAudit_uuid() {
    return audit_uuid;
}

public void setAudit_uuid(String audit_uuid) {
    this.audit_uuid = audit_uuid;
}

public String getAudit_uid() {
    return audit_uid;
}

public void setAudit_uid(String audit_uid) {
    this.audit_uid = audit_uid;
}

public String getAudit_ws_id() {
    return audit_ws_id;
}

public void setAudit_ws_id(String audit_ws_id) {
    this.audit_ws_id = audit_ws_id;
}

public Long getAudit_pid() {
    return audit_pid;
}

public void setAudit_pid(Long audit_pid) {
    this.audit_pid = audit_pid;
}

public java.time.OffsetDateTime getAudit_host_time() {
    return audit_host_time;
}

public void setAudit_host_time(java.time.OffsetDateTime audit_host_time) {
    this.audit_host_time = audit_host_time;
}

public java.time.OffsetDateTime getAudit_local_time() {
    return audit_local_time;
}

public void setAudit_local_time(java.time.OffsetDateTime audit_local_time) {
    this.audit_local_time = audit_local_time;
}

public java.time.OffsetDateTime getAudit_acceptance_time() {
    return audit_acceptance_time;
}

public void setAudit_acceptance_time(java.time.OffsetDateTime audit_acceptance_time) {
    this.audit_acceptance_time = audit_acceptance_time;
}

public java.time.OffsetDateTime getAudit_acceptance_time_utc() {
    return audit_acceptance_time_utc;
}

public void setAudit_acceptance_time_utc(java.time.OffsetDateTime audit_acceptance_time_utc) {
    this.audit_acceptance_time_utc = audit_acceptance_time_utc;
}

}
