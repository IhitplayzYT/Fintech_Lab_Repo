package com.ihit.lab4.dto;

import java.sql.Date;

public class CustomerClassificationDTO {
    private Long id;
    private String type;
    private String value;
    private Date effectiveDate;
    private Long detailId;
    private Long nameId;

    public CustomerClassificationDTO(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }

    public String getType(){ return type; }
    public void setType(String type){ this.type=type; }

    public String getValue(){ return value; }
    public void setValue(String value){ this.value=value; }

    public Date getEffectiveDate(){ return effectiveDate; }
    public void setEffectiveDate(Date effectiveDate){ this.effectiveDate=effectiveDate; }

    public Long getDetailId(){ return detailId; }
    public void setDetailId(Long detailId){ this.detailId=detailId; }

    public Long getNameId(){ return nameId; }
    public void setNameId(Long nameId){ this.nameId=nameId; }
}
