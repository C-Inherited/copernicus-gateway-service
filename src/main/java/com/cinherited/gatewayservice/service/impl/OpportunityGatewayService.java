package com.cinherited.gatewayservice.service.impl;

import com.cinherited.gatewayservice.clients.OpportunityClient;
import com.cinherited.gatewayservice.clients.StatsClient;
import com.cinherited.gatewayservice.controllers.impl.GatewayController;
import com.cinherited.gatewayservice.controllers.impl.OpportunityGatewayController;
import com.cinherited.gatewayservice.dtos.OpportunityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OpportunityGatewayService {

    @Autowired
    OpportunityClient opportunityClient;

    private final CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();
    private CircuitBreaker opportunityCircuit = circuitBreakerFactory.create("opportunity-service");

    /** ACCOUNT STATS **/
    public OpportunityDTO getOpportunity(Integer id) {
        return opportunityCircuit.run(() -> opportunityClient.getOpportunity(id, "Bearer "+ OpportunityGatewayController.getOpportunityAuthOk()), throwable -> (OpportunityDTO) opportunityFallback());
    }

    public List<OpportunityDTO> getAllOpportunities() {
        return opportunityCircuit.run(() -> opportunityClient.getAllOpportunities("Bearer "+ OpportunityGatewayController.getOpportunityAuthOk()), throwable -> (List<OpportunityDTO>) opportunityFallback());
    }

    public OpportunityDTO postOpportunity(OpportunityDTO opportunityDTO) {
        return opportunityCircuit.run(() -> opportunityClient.postOpportunity(opportunityDTO, "Bearer "+ OpportunityGatewayController.getOpportunityAuthOk()), throwable -> (OpportunityDTO) opportunityFallback());
    }

    public OpportunityDTO putOpportunity(Integer id, OpportunityDTO opportunityDTO) {
        return opportunityCircuit.run(() -> opportunityClient.putOpportunity(id, opportunityDTO, "Bearer "+ OpportunityGatewayController.getOpportunityAuthOk()), throwable -> (OpportunityDTO) opportunityFallback());
    }

    public List<OpportunityDTO> findOpportunitiesBySalesRep(Integer salesRepId, Optional<String> status) {
        return opportunityCircuit.run(() -> opportunityClient.findOpportunitiesBySalesRep(salesRepId, status, "Bearer "+ OpportunityGatewayController.getOpportunityAuthOk()), throwable -> (List<OpportunityDTO>) opportunityFallback());
    }

    private Object opportunityFallback(){
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Stats service is not available right now");
    }

}
