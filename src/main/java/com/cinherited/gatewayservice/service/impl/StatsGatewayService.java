package com.cinherited.gatewayservice.service.impl;

import com.cinherited.gatewayservice.clients.StatsClient;
import com.cinherited.gatewayservice.controllers.impl.GatewayController;
import com.cinherited.gatewayservice.controllers.impl.StatsGatewayController;
import com.cinherited.gatewayservice.service.interfaces.IStatsGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StatsGatewayService implements IStatsGatewayService {

    @Autowired
    StatsClient statsClient;

    private final CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();
    private CircuitBreaker statsCircuit = circuitBreakerFactory.create("stats-service");

    /** ACCOUNT STATS **/
    public Double avgEmployeeCount() {
        return statsCircuit.run(() -> statsClient.avgEmployeeCount("Bearer "+ StatsGatewayController.getStatsAuthOk()),
                throwable -> (Double) statsFallback());
    }

    public Integer maxEmployeeCount() {
        return statsCircuit.run(() -> statsClient.maxEmployeeCount("Bearer "+ StatsGatewayController.getStatsAuthOk()),
                throwable -> (Integer) statsFallback());
    }

    public Integer minEmployeeCount() {
        return statsCircuit.run(() -> statsClient.minEmployeeCount("Bearer "+ StatsGatewayController.getStatsAuthOk()),
                throwable -> (Integer) statsFallback());
    }

    public Double medianEmployeeCount() {
        return statsCircuit.run(() -> statsClient.medianEmployeeCount("Bearer "+ StatsGatewayController.getStatsAuthOk()),
                throwable -> (Double) statsFallback());
    }

    /** OPPORTUNITY STATS **/
    public List<Object[]> countOpportunitiesByProduct(Optional<String> status) {
        return statsCircuit.run(() -> statsClient.countOpportunitiesByProduct(status, "Bearer "+ StatsGatewayController.getStatsAuthOk()),
                throwable -> (List<Object[]>) statsFallback());
    }

    public List<Object[]> countOpportunitiesByCity(Optional<String> status) {
        return statsCircuit.run(() -> statsClient.countOpportunitiesByCity(status, "Bearer "+ StatsGatewayController.getStatsAuthOk()),
                throwable -> (List<Object[]>) statsFallback());
    }

    public List<Object[]> countOpportunitiesByCountry(Optional<String> status) {
        return statsCircuit.run(() -> statsClient.countOpportunitiesByCountry(status, "Bearer "+ StatsGatewayController.getStatsAuthOk()),
                throwable -> (List<Object[]>) statsFallback());
    }

    public List<Object[]> countOpportunitiesByIndustry(Optional<String> status) {
        return statsCircuit.run(() -> statsClient.countOpportunitiesByIndustry(status, "Bearer "+ StatsGatewayController.getStatsAuthOk()),
                throwable -> (List<Object[]>) statsFallback());
    }

    public Double avgOpportunitiesByAccount() {
        return statsCircuit.run(() -> statsClient.avgOpportunitiesByAccount("Bearer "+ StatsGatewayController.getStatsAuthOk()),
                throwable -> (Double) statsFallback());
    }

    public Integer maxOpportunitiesByAccount() {
        return statsCircuit.run(() -> statsClient.maxOpportunitiesByAccount("Bearer "+ StatsGatewayController.getStatsAuthOk()),
                throwable -> (Integer) statsFallback());
    }

    public Integer minOpportunitiesByAccount() {
        return statsCircuit.run(() -> statsClient.minOpportunitiesByAccount("Bearer "+ StatsGatewayController.getStatsAuthOk()),
                throwable -> (Integer) statsFallback());
    }

    public Double medianOpportunitiesByAccount() {
        return statsCircuit.run(() -> statsClient.medianOpportunitiesByAccount("Bearer "+ StatsGatewayController.getStatsAuthOk()),
                throwable -> (Double) statsFallback());
    }

    public List<Object[]> avgQuantityByProduct() {
        return statsCircuit.run(() -> statsClient.avgQuantityByProduct("Bearer "+ StatsGatewayController.getStatsAuthOk()),
                throwable -> (List<Object[]>) statsFallback());
    }

    public List<Object[]> maxQuantityByProduct() {
        return statsCircuit.run(() -> statsClient.maxQuantityByProduct("Bearer "+ StatsGatewayController.getStatsAuthOk()),
                throwable -> (List<Object[]>) statsFallback());
    }

    public List<Object[]> minQuantityByProduct() {
        return statsCircuit.run(() -> statsClient.minQuantityByProduct("Bearer "+ StatsGatewayController.getStatsAuthOk()),
                throwable -> (List<Object[]>) statsFallback());
    }

    public List<Object[]> medianQuantityByProduct() {
        return statsCircuit.run(() -> statsClient.medianQuantityByProduct("Bearer "+ StatsGatewayController.getStatsAuthOk()),
                throwable -> (List<Object[]>) statsFallback());
    }

    private Object statsFallback(){
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Stats service is not available right now");
    }
}
