package com.cinherited.gatewayservice.services.interfaces;

import com.cinherited.gatewayservice.dtos.AuthenticationRequest;
import com.cinherited.gatewayservice.dtos.LeadDTO;

import java.util.List;

public interface ILeadsServices {
    List<LeadDTO> findAll(String jwt);

    List<LeadDTO> findAllBySalesRepId(String jwt, int salesRepId);

    LeadDTO findByLeadId(String jwt, int leadId);

    LeadDTO createNewLead(String jwt, LeadDTO leadDTO);

    LeadDTO updateLead(String jwt, LeadDTO leadDTO);

    int deleteLead(String jwt, int leadId);
}
