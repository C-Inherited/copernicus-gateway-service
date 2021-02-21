package com.cinherited.gatewayservice.controllers.interfaces;

import com.cinherited.gatewayservice.dtos.LeadDTO;

import java.util.List;

public interface IGatewayController {

    List<LeadDTO> findAllLeads();
}
