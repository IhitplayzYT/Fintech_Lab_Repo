package com.ihit.lab4.entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
@Entity
@Table(name="customer_detail")
public class CustomerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_identifier;

    private String customer_gender;
    private String customer_type;
    private Date customer_dob;
    private String customer_lang;
    private String customer_status;
    private String customer_country;

// ===== AUDIT FIELDS =====
@Column(name = "crud_flag", length = 1)
private String crud_flag;
@Column(name = "audit_uuid", length = 100, nullable = true, updatable = false)
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

    public CustomerDetail(){}

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

    public String getCustomer_gender() {
        return customer_gender;
    }

    public void setCustomer_gender(String customer_gender) {
        this.customer_gender = customer_gender;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public Date getCustomer_dob() {
        return customer_dob;
    }

    public void setCustomer_dob(Date customer_dob) {
        this.customer_dob = customer_dob;
    }

    public String getCustomer_lang() {
        return customer_lang;
    }

    public void setCustomer_lang(String customer_lang) {
        this.customer_lang = customer_lang;
    }

    public String getCustomer_status() {
        return customer_status;
    }

    public void setCustomer_status(String customer_status) {
        this.customer_status = customer_status;
    }

    public String getCustomer_country() {
        return customer_country;
    }

    public void setCustomer_country(String customer_country) {
        this.customer_country = customer_country;
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

