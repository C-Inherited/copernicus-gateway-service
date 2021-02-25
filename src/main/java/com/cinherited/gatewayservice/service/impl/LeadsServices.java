package com.cinherited.gatewayservice.service.impl;

import com.cinherited.gatewayservice.clients.AccountClient;
import com.cinherited.gatewayservice.clients.ContactClient;
import com.cinherited.gatewayservice.clients.LeadClient;
import com.cinherited.gatewayservice.clients.OpportunityClient;
import com.cinherited.gatewayservice.dtos.*;
import com.cinherited.gatewayservice.service.interfaces.ILeadsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeadsServices implements ILeadsServices {

    @Autowired
    private LeadClient leadClient;
    @Autowired
    private ContactClient contactClient;
    @Autowired
    private AccountClient accountClient;
    @Autowired
    private OpportunityClient opportunityClient;
    @Override
    public List<LeadDTO> findAll(String leadsAuthOk) {
        return leadClient.findAll(bearer + leadsAuthOk);
    }

    private String bearer = "Bearer ";

    @Override
    public List<LeadDTO> findAllBySalesRepId(String jwt, int salesRepId) {
        return leadClient.findAllBySalesRepId( salesRepId, bearer + jwt);
    }

    @Override
    public LeadDTO findByLeadId(String jwt, int leadId) {
        return leadClient.findByLeadId(leadId,bearer + jwt);
    }

    @Override
    public LeadDTO createNewLead(String jwt, LeadDTO leadDTO) {
        return leadClient.createNewLead(leadDTO,bearer + jwt);
    }

    @Override
    public LeadDTO updateLead(String jwt, LeadDTO leadDTO) {
        return leadClient.updateLead(leadDTO, bearer + jwt);
    }

    @Override
    public int deleteLead(String jwt, int leadId) {
        return leadClient.deleteLead(leadId, bearer + jwt);
    }

    @Override
    public ConversionDTO convertLead(String jwt, int leadId, Integer accountId, OpportunityDTO opportunityDTO) {
        LeadDTO leadDTO = findByLeadId(jwt, leadId);
        AccountDTO accountDTO = accountClient.getAccount(accountId, jwt);

        ContactDTO contactDTO = new ContactDTO(leadDTO.getLeadName(), leadDTO.getLeadPhone(), leadDTO.getLeadEmail(), leadDTO.getLeadCompanyName(), accountId);
        contactDTO = contactClient.postContact(contactDTO, jwt);

        opportunityDTO = opportunityClient.postOpportunity(opportunityDTO, jwt);

        leadClient.deleteLead(leadId, jwt);

        return new ConversionDTO(accountDTO, contactDTO, opportunityDTO);
    }
}
