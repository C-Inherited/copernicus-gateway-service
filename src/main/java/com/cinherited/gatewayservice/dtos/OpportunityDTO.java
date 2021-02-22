package com.cinherited.gatewayservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;


public class OpportunityDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    private String product;
    private Integer quantity;
    private Integer contactId;
    private Integer salesRepId;
    private Integer accountId;

    private String status;

    public OpportunityDTO() {
    }

    public OpportunityDTO(Integer id, String product, Integer quantity, Integer contactId, Integer salesRepId, Integer accountId, String status) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.contactId = contactId;
        this.salesRepId = salesRepId;
        this.accountId = accountId;
        this.status = status;
    }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(Integer salesRepId) {
        this.salesRepId = salesRepId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
