package com.cinherited.gatewayservice.services.impl;

import com.cinherited.gatewayservice.clients.LeadClient;
import com.cinherited.gatewayservice.dtos.AuthenticationRequest;
import com.cinherited.gatewayservice.dtos.LeadDTO;
import com.cinherited.gatewayservice.services.interfaces.ILeadsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadsServices implements ILeadsServices {

    @Autowired
    private LeadClient leadClient;
    @Override
    public List<LeadDTO> findAll(String leadsAuthOk) {
        return leadClient.findAll(bearer + leadsAuthOk);
    }

    private String bearer = "Bearer ";

    @Override
    public List<LeadDTO> findAllBySalesRepId(String jwt, int salesRepId) {
        return leadClient.findAllBySalesRepId(authenticationRequest, salesRepId);
    }

    @Override
    public LeadDTO findByLeadId(String jwt, int leadId) {
        return leadClient.findByLeadId(authenticationRequest, leadId);
    }

    @Override
    public LeadDTO createNewLead(String jwt, LeadDTO leadDTO) {
        return leadClient.createNewLead(authenticationRequest, leadDTO);
    }

    @Override
    public LeadDTO updateLead(String jwt, LeadDTO leadDTO) {
        return leadClient.updateLead(authenticationRequest, leadDTO);
    }

    @Override
    public int deleteLead(String jwt, int leadId) {
        return leadClient.deleteLead(jwt, leadId);
    }
}
