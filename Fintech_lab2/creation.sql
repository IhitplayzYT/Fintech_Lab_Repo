CREATE TABLE cust_dtl (
    cst_idfr BIGINT(20) PRIMARY KEY,
    cust_crud_value CHAR(1),
    cust_prgm_id VARCHAR(255),
    cust_acpt_ts DATETIME(6),
    cust_acpt_ts_utc_ofst DATETIME(6),
    cust_cntry_orgtn VARCHAR(255),
    cust_dob DATE,
    cust_gendr VARCHAR(255),
    cstid_idfr BIGINT(20),
    cust_prfrd_lng VARCHAR(255),
    cust_status VARCHAR(255),
    cust_type VARCHAR(255),
    cust_efctv_dt DATE,
    cust_host_ts DATETIME(6),
    cust_local_ts DATETIME(6),
    cust_user_id VARCHAR(255),
    cust_uuid VARCHAR(255),
    cust_ws_id VARCHAR(255)
);

CREATE TABLE cust_dtl_backup (
    cst_idfr BIGINT(20) PRIMARY KEY,
    cust_crud_value CHAR(1),
    cust_prgm_id VARCHAR(255),
    cust_acpt_ts DATETIME(6),
    cust_acpt_ts_utc_ofst DATETIME(6),
    cust_cntry_orgtn VARCHAR(255),
    cust_dob DATE,
    cust_gendr VARCHAR(255),
    cstid_idfr BIGINT(20),
    cust_prfrd_lng VARCHAR(255),
    cust_status VARCHAR(255),
    cust_type VARCHAR(255),
    cust_efctv_dt DATE,
    cust_host_ts DATETIME(6),
    cust_local_ts DATETIME(6),
    cust_user_id VARCHAR(255),
    cust_uuid VARCHAR(255),
    cust_ws_id VARCHAR(255)
);

CREATE TABLE cstmr_signin (
    usr_idfr BIGINT(20) PRIMARY KEY,
    usr_crud_value CHAR(1),
    usr_prgm_id VARCHAR(255),
    usr_acpt_ts DATETIME(6),
    usr_acpt_ts_utc_ofst DATETIME(6),
    usr_efctv_dt DATE,
    usr_host_ts DATETIME(6),
    usr_local_ts DATETIME(6),
    usr_password VARCHAR(255),
    usr_user_id VARCHAR(255),
    usr_usrname VARCHAR(255),
    usr_uuid VARCHAR(255),
    usr_ws_id VARCHAR(255),
    cstid_idfr BIGINT(20),
    INDEX idx_signin_cstid (cstid_idfr),
    FOREIGN KEY (cstid_idfr) REFERENCES cust_dtl(cst_idfr)
);

CREATE TABLE cust_addr_cmpnt (
    id BIGINT(20) PRIMARY KEY,
    cstadcmp_crud_value CHAR(1),
    cstadcmp_prgm_id VARCHAR(255),
    cstadcmp_acpt_ts DATETIME(6),
    cstadcmp_acpt_ts_utc_ofst DATETIME(6),
    cstadcmp_type_id VARCHAR(255),
    cstadcmp_type_value VARCHAR(255),
    cstadcmp_efctv_dt DATE,
    cstadcmp_host_ts DATETIME(6),
    cstadcmp_local_ts DATETIME(6),
    cstadcmp_user_id VARCHAR(255),
    cstadcmp_uuid VARCHAR(255),
    cstadcmp_ws_id VARCHAR(255),
    cstid_idfr BIGINT(20),
    INDEX idx_addr_cstid (cstid_idfr),
    FOREIGN KEY (cstid_idfr) REFERENCES cust_dtl(cst_idfr)
);

CREATE TABLE cust_nme_cmpnt (
    id BIGINT(20) PRIMARY KEY,
    cstnmcmp_crud_value CHAR(1),
    cstnmcmp_prgm_id VARCHAR(255),
    cstnmcmp_acpt_ts DATETIME(6),
    cstnmcmp_acpt_ts_utc_ofst DATETIME(6),
    cstid_idfr BIGINT(20),
    cstnmcmp_type_id VARCHAR(255),
    cstnmcmp_type_value VARCHAR(255),
    cstnmcmp_efctv_dt DATE,
    cstnmcmp_host_ts DATETIME(6),
    cstnmcmp_local_ts DATETIME(6),
    cstnmcmp_user_id VARCHAR(255),
    cstnmcmp_uuid VARCHAR(255),
    cstnmcmp_ws_id VARCHAR(255),
    INDEX idx_name_cstid (cstid_idfr),
    FOREIGN KEY (cstid_idfr) REFERENCES cust_dtl(cst_idfr)
);

CREATE TABLE cust_cntct_dtls (
    cstcndt_id BIGINT(20) PRIMARY KEY,
    cstcndt_crud_value CHAR(1),
    cstcndt_prgm_id VARCHAR(255),
    cstcndt_acpt_ts DATETIME(6),
    cstcndt_acpt_ts_utc_ofst DATETIME(6),
    cstcndt_value VARCHAR(255),
    cstcndt_end_dt DATE,
    cstcndt_host_ts DATETIME(6),
    cstcndt_local_ts DATETIME(6),
    cstcndt_strt_dt DATE,
    cstcndt_user_id VARCHAR(255),
    cstcndt_uuid VARCHAR(255),
    cstcndt_ws_id VARCHAR(255),
    cstid_idfr BIGINT(20),
    INDEX idx_cntct_cstid (cstid_idfr),
    FOREIGN KEY (cstid_idfr) REFERENCES cust_dtl(cst_idfr)
);

CREATE TABLE cust_idfn (
    cstin_idfr BIGINT(20) PRIMARY KEY,
    cstin_crud_value CHAR(1),
    cstin_prgm_id VARCHAR(255),
    cstin_acpt_ts DATETIME(6),
    cstin_acpt_ts_utc_ofst DATETIME(6),
    cstin_id_item VARCHAR(255),
    cstin_id_type VARCHAR(255),
    cstin_efctv_dt DATE,
    cstin_host_ts DATETIME(6),
    cstin_local_ts DATETIME(6),
    cstin_user_id VARCHAR(255),
    cstin_uuid VARCHAR(255),
    cstin_ws_id VARCHAR(255)
);

CREATE TABLE cust_cl (
    cstcl_id BIGINT(20) PRIMARY KEY,
    cstcl_crud_value CHAR(1),
    cstcl_typ_value VARCHAR(255),
    cstcl_prgm_id VARCHAR(255),
    cstcl_acpt_ts DATETIME(6),
    cstcl_acpt_ts_utc_ofst DATETIME(6),
    cstcl_typ VARCHAR(255),
    cstcl_efctv_dt DATE,
    cstcl_host_ts DATETIME(6),
    cstcl_local_ts DATETIME(6),
    cstcl_user_id VARCHAR(255),
    cstcl_uuid VARCHAR(255),
    cstcl_ws_id VARCHAR(255)
);

CREATE TABLE cust_pridn (
    id BIGINT(20) PRIMARY KEY,
    cstprid_crud_value CHAR(1),
    cstprid_prgm_id VARCHAR(255),
    cstprid_acpt_ts DATETIME(6),
    cstprid_acpt_ts_utc_ofst DATETIME(6),
    cstprid_value VARCHAR(255),
    cstprid_end_dt DATE,
    cstprid_host_ts DATETIME(6),
    cstprid_local_ts DATETIME(6),
    cstprid_strt_dt DATE,
    cstprid_user_id VARCHAR(255),
    cstprid_uuid VARCHAR(255),
    cstprid_ws_id VARCHAR(255),
    cstcl_id BIGINT(20),
    cstid_idfr BIGINT(20),
    INDEX idx_pridn_cstid (cstid_idfr),
    INDEX idx_pridn_cstcl (cstcl_id),
    FOREIGN KEY (cstid_idfr) REFERENCES cust_dtl(cst_idfr),
    FOREIGN KEY (cstcl_id) REFERENCES cust_cl(cstcl_id)
);

CREATE TABLE execution_log (
    id BIGINT(20) PRIMARY KEY,
    execution_time BIGINT(20),
    method_name VARCHAR(255),
    parameters LONGTEXT,
    result LONGTEXT,
    success BIT(1),
    timestamp DATETIME(6)
);











