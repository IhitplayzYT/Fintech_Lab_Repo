package com.ihit.lab4.dto;

import java.sql.Date;

public class CustomerIdentificationDTO {
    private Long id;
    private String type;
    private String item;
    private Date effectiveDate;
    private Long detailId;

    public CustomerIdentificationDTO(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }

    public String getType(){ return type; }
    public void setType(String type){ this.type=type; }

    public String getItem(){ return item; }
    public void setItem(String item){ this.item=item; }

    public Date getEffectiveDate(){ return effectiveDate; }
    public void setEffectiveDate(Date effectiveDate){ this.effectiveDate=effectiveDate; }

    public Long getDetailId(){ return detailId; }
    public void setDetailId(Long detailId){ this.detailId=detailId; }
}
