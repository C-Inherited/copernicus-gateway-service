package com.cinherited.gatewayservice.service.interfaces;

import com.cinherited.gatewayservice.dtos.OpportunityDTO;

import java.util.List;
import java.util.Optional;

public interface IOpportunityGatewayService {
    OpportunityDTO getOpportunity(Integer id);

    List<OpportunityDTO> getAllOpportunities();

    OpportunityDTO putOpportunity(Integer id, OpportunityDTO opportunityDTO);

    List<OpportunityDTO> findOpportunitiesBySalesRep(Integer salesRepId, Optional<String> status);

}
