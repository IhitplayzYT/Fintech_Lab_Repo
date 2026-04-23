package com.ihit.lab4.dto;

import java.sql.Date;

public class CustomerDetailDTO {
    private Long id;
    private String gender;
    private String type;
    private Date dob;
    private String lang;
    private String status;
    private String country;

    public CustomerDetailDTO(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }

    public String getGender(){ return gender; }
    public void setGender(String gender){ this.gender=gender; }

    public String getType(){ return type; }
    public void setType(String type){ this.type=type; }

    public Date getDob(){ return dob; }
    public void setDob(Date dob){ this.dob=dob; }

    public String getLang(){ return lang; }
    public void setLang(String lang){ this.lang=lang; }

    public String getStatus(){ return status; }
    public void setStatus(String status){ this.status=status; }

    public String getCountry(){ return country; }
    public void setCountry(String country){ this.country=country; }
}
