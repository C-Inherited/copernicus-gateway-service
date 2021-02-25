package com.cinherited.gatewayservice.dtos;

public class ConversionDTO {
    private AccountDTO accountDTO;
    private ContactDTO contactDTO;
    private OpportunityDTO opportunityDTO;

    public ConversionDTO() {
    }

    public ConversionDTO(AccountDTO accountDTO, ContactDTO contactDTO, OpportunityDTO opportunityDTO) {
        this.accountDTO = accountDTO;
        this.contactDTO = contactDTO;
        this.opportunityDTO = opportunityDTO;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }

    public ContactDTO getContactDTO() {
        return contactDTO;
    }

    public void setContactDTO(ContactDTO contactDTO) {
        this.contactDTO = contactDTO;
    }

    public OpportunityDTO getOpportunityDTO() {
        return opportunityDTO;
    }

    public void setOpportunityDTO(OpportunityDTO opportunityDTO) {
        this.opportunityDTO = opportunityDTO;
    }
}
