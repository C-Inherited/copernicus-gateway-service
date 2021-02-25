package com.cinherited.gatewayservice.services.impl;

import com.cinherited.gatewayservice.clients.LeadClient;
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
        return leadClient.findAll("Bearer " + leadsAuthOk);
    }
}
