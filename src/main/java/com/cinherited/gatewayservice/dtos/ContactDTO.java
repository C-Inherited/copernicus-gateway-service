package com.cinherited.gatewayservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;


public class ContactDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;
    private Integer accountId;

    public ContactDTO() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
