package com.cinherited.gatewayservice.dtos;

public class SalesRepDTO {

    private Integer salesRepId;
    private String name;

    public SalesRepDTO() {
    }

    public SalesRepDTO(String name) {
        this.name = name;
    }

    public SalesRepDTO(Integer salesRepId, String name) {
        this.salesRepId = salesRepId;
        this.name = name;
    }

    public Integer getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(Integer salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
