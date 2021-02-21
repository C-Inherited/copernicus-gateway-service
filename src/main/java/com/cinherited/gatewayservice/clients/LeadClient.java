package com.cinherited.gatewayservice.clients;

import com.cinherited.gatewayservice.dtos.LeadDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("leads-service")
public interface LeadClient {


    @GetMapping("/leads/all")
    List<LeadDTO> findAll();
}
