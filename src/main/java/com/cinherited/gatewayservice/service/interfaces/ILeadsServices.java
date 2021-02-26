package com.cinherited.gatewayservice.service.interfaces;

import com.cinherited.gatewayservice.dtos.AccountDTO;
import com.cinherited.gatewayservice.dtos.LeadDTO;
import com.cinherited.gatewayservice.dtos.OpportunityDTO;

import java.util.List;

public interface ILeadsServices {
    List<LeadDTO> findAll(String jwt);

    List<LeadDTO> findAllBySalesRepId(String jwt, int salesRepId);

    LeadDTO findByLeadId(String jwt, int leadId);

    LeadDTO createNewLead(String jwt, LeadDTO leadDTO);

    LeadDTO updateLead(String jwt, LeadDTO leadDTO);

    int deleteLead(String jwt, int leadId);

    AccountDTO convertLead(String jwt, int leadId, Integer accountId, OpportunityDTO opportunityDTO);
}
