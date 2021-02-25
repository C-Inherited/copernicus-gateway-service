package com.cinherited.gatewayservice.dtos;

import javax.validation.constraints.NotEmpty;

public class SalesRepNameDTO {

    @NotEmpty
    private String name;

    public SalesRepNameDTO() {
    }

    public SalesRepNameDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
