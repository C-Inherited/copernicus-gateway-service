package com.cinherited.gatewayservice.services.interfaces;

import com.cinherited.gatewayservice.dtos.LeadDTO;

import java.util.List;

public interface ILeadsServices {
    List<LeadDTO> findAll(String s);
}
