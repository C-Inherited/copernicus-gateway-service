package com.cinherited.gatewayservice.clients;

import com.cinherited.gatewayservice.dtos.AuthenticationRequest;
import com.cinherited.gatewayservice.dtos.OpportunityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient("opportunity-service")
public interface OpportunityClient {
    @GetMapping("/opportunity/{id}")
    OpportunityDTO getOpportunity(@PathVariable Integer id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/opportunity/all")
    List<OpportunityDTO> getAllOpportunities(@RequestHeader(value = "Authorization") String authorizationHeader);

    @PostMapping("/opportunity")
    OpportunityDTO postOpportunity(@RequestBody OpportunityDTO opportunityDTO, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PutMapping("/opportunity/{id}")
    OpportunityDTO putOpportunity(@PathVariable Integer id, @RequestBody OpportunityDTO opportunityDTO, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/opportunity/all/{salesRepId}")
    List<OpportunityDTO> findOpportunitiesBySalesRep(@PathVariable Integer salesRepId, @RequestParam Optional<String> status, @RequestHeader(value = "Authorization") String authorizationHeader);

    @RequestMapping(value = "opportunity/authenticate", method = RequestMethod.POST)
    ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest);

}
