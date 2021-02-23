package com.cinherited.gatewayservice.controllers.impl;

import com.cinherited.gatewayservice.clients.LeadClient;
import com.cinherited.gatewayservice.clients.StatsClient;
import com.cinherited.gatewayservice.controllers.interfaces.IGatewayController;
import com.cinherited.gatewayservice.dtos.LeadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/copernicus")
public class GatewayController implements IGatewayController {

    @Autowired
    private LeadClient leadClient;

    @Override
    @GetMapping("/leads/all")
    public List<LeadDTO> findAllLeads() {

        return leadClient.findAll();
    }

}
