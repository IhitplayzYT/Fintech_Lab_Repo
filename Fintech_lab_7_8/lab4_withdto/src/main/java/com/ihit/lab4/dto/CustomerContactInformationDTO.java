package com.ihit.lab4.dto;

import java.sql.Date;

public class CustomerContactInformationDTO {
    private Long id;
    private String type;
    private String value;
    private Date effectiveDate;
    private Date startDate;
    private Date endDate;
    private Long detailId;

    public CustomerContactInformationDTO(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }

    public String getType(){ return type; }
    public void setType(String type){ this.type=type; }

    public String getValue(){ return value; }
    public void setValue(String value){ this.value=value; }

    public Date getEffectiveDate(){ return effectiveDate; }
    public void setEffectiveDate(Date effectiveDate){ this.effectiveDate=effectiveDate; }

    public Date getStartDate(){ return startDate; }
    public void setStartDate(Date startDate){ this.startDate=startDate; }

    public Date getEndDate(){ return endDate; }
    public void setEndDate(Date endDate){ this.endDate=endDate; }

    public Long getDetailId(){ return detailId; }
    public void setDetailId(Long detailId){ this.detailId=detailId; }
}
