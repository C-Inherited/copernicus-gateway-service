package com.cinherited.gatewayservice.dtos;

import java.util.List;

public class CompleteSalesRepDTO {

    private Integer salesRepId;
    private String name;

    private List<OpportunityDTO> opportunities;

    private List<LeadDTO> leads;

    public CompleteSalesRepDTO() {
    }

    public CompleteSalesRepDTO(Integer salesRepId, String name, List<OpportunityDTO> opportunities, List<LeadDTO> leads) {
        this.salesRepId = salesRepId;
        this.name = name;
        this.opportunities = opportunities;
        this.leads = leads;
    }

    public CompleteSalesRepDTO(String name) {
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

    public List<OpportunityDTO> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List<OpportunityDTO> opportunities) {
        this.opportunities = opportunities;
    }

    public List<LeadDTO> getLeads() {
        return leads;
    }

    public void setLeads(List<LeadDTO> leads) {
        this.leads = leads;
    }
}
