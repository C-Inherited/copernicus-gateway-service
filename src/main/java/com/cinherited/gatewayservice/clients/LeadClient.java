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

    @GetMapping("/leads/all/{salesRepId}")
    List<LeadDTO> findAllBySalesRepId(@PathVariable int salesRepId, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/leads/{leadId}")
    LeadDTO findByLeadId(@PathVariable int leadId, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PostMapping("leads/new")
    LeadDTO createNewLead(@RequestBody LeadDTO leadDTO, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PutMapping("leads/update")
    LeadDTO updateLead(@RequestBody LeadDTO leadDTO, @RequestHeader(value = "Authorization") String authorizationHeader);

    @DeleteMapping("leads/delete/{leadId}")
    int deleteLead(@PathVariable int leadId, @RequestHeader(value = "Authorization") String authorizationHeader);

}
