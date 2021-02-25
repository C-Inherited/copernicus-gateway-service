package com.cinherited.gatewayservice.controllers.impl;

import com.cinherited.gatewayservice.dtos.OpportunityDTO;
import com.cinherited.gatewayservice.security.MyUserDetailsService;
import com.cinherited.gatewayservice.service.impl.OpportunityGatewayService;
import com.cinherited.gatewayservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/copernicus")
public class OpportunityGatewayController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    private static String opportunityAuthOk;

    @Autowired
    OpportunityGatewayService opportunityGatewayService;

    @GetMapping("/opportunity/{id}")
    public OpportunityDTO getOpportunity(@PathVariable Integer id) {
        return opportunityGatewayService.getOpportunity(id);
    }

    @GetMapping("/opportunity/all")
    public List<OpportunityDTO> getAllOpportunities() {
        return opportunityGatewayService.getAllOpportunities();
    }

    @PostMapping("/opportunity")
    public OpportunityDTO postOpportunity(@RequestBody OpportunityDTO opportunityDTO) {
        return opportunityGatewayService.postOpportunity(opportunityDTO);
    }

    @PutMapping("/opportunity/{id}")
    public OpportunityDTO putOpportunity(@PathVariable Integer id, @RequestBody OpportunityDTO opportunityDTO) {
        return opportunityGatewayService.putOpportunity(id, opportunityDTO);
    }

    @GetMapping("/opportunity/all/{salesRepId}")
    public List<OpportunityDTO> findOpportunitiesBySalesRep(@PathVariable Integer salesRepId, @RequestParam Optional<String> status) {
        return opportunityGatewayService.findOpportunitiesBySalesRep(salesRepId, status);
    }

    public static String getOpportunityAuthOk() {
        return opportunityAuthOk;
    }

    public static void setOpportunityAuthOk(String opportunityAuthOk) {
        OpportunityGatewayController.opportunityAuthOk = opportunityAuthOk;
    }

}
