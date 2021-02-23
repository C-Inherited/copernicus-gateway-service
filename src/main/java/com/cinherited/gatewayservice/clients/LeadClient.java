package com.cinherited.gatewayservice.clients;

import com.cinherited.gatewayservice.dtos.AuthenticationRequest;
import com.cinherited.gatewayservice.dtos.LeadDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("leads-service")
public interface LeadClient {


    @RequestMapping(value = "/leads/authenticate", method = RequestMethod.POST)
    ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest);

    @GetMapping("/leads/all")
    List<LeadDTO> findAll(@RequestHeader(value = "Authorization") String authorizationHeader);
}
