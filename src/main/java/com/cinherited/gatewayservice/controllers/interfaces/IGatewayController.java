package com.cinherited.gatewayservice.controllers.interfaces;

import com.cinherited.gatewayservice.dtos.LeadDTO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

public interface IGatewayController {

    List<LeadDTO> findAllLeads();
}
