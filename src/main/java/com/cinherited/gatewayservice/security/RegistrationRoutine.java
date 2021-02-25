package com.cinherited.gatewayservice.security;

import com.cinherited.gatewayservice.clients.OpportunityClient;
import com.cinherited.gatewayservice.clients.StatsClient;
import com.cinherited.gatewayservice.controllers.impl.OpportunityGatewayController;
import com.cinherited.gatewayservice.controllers.impl.StatsGatewayController;
import com.cinherited.gatewayservice.dtos.AuthenticationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


@Component
public class RegistrationRoutine {

    @Autowired
    OpportunityClient opportunityClient;

    @Autowired
    StatsClient statsClient;

    public static boolean isOpportunityRegistered = false;
    public static boolean isStatsRegistered = false;

    private static final Logger log = LoggerFactory.getLogger(RegistrationRoutine.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    @Scheduled(fixedRate = 10000)
    public void checkRegistrationOpportunity() {
        if (!isOpportunityRegistered){
            CircuitBreaker circuitBreaker = circuitBreakerFactory.create("opportunity-service");
            log.info("Trying to register with opportunity-service {}", dateFormat.format(new Date()));
            AuthenticationRequest authenticationRequest = new AuthenticationRequest("gateway", "gateway");
            ResponseEntity<?> responseEntity= circuitBreaker.run(() -> opportunityClient.createAuthenticationToken(authenticationRequest), throwable -> fallbackTransaction("opportunity-service"));
            if (responseEntity != null) {
                parseJWTOpportunity(responseEntity);
                isOpportunityRegistered = true;
                log.info("Registered with opportunity-service auth token: {}", OpportunityGatewayController.getOpportunityAuthOk());
            }
        }
    }

    @Scheduled(fixedRate = 10000)
    public void checkRegistrationAccount() {
        if (!isStatsRegistered){
            CircuitBreaker circuitBreaker = circuitBreakerFactory.create("stats-service");
            log.info("Trying to register with stats-service {}", dateFormat.format(new Date()));
            AuthenticationRequest authenticationRequest = new AuthenticationRequest("gateway", "gateway");
            ResponseEntity<?> responseEntity= circuitBreaker.run(() -> statsClient.createAuthenticationToken(authenticationRequest), throwable -> fallbackTransaction("stats-service"));
            if (responseEntity != null) {
                parseJWTStats(responseEntity);
                isStatsRegistered = true;
                log.info("Registered with stats-service auth token: {}", StatsGatewayController.getStatsAuthOk());
            }
        }
    }

    private void parseJWTOpportunity(ResponseEntity<?> responseEntity) {
        String auth = Objects.requireNonNull(responseEntity.getBody()).toString();
        OpportunityGatewayController.setOpportunityAuthOk(auth.substring(5, auth.length() - 1));
    }

    private void parseJWTStats(ResponseEntity<?> responseEntity) {
        String auth = Objects.requireNonNull(responseEntity.getBody()).toString();
        StatsGatewayController.setStatsAuthOk(auth.substring(5, auth.length() - 1));
    }

    private ResponseEntity<?> fallbackTransaction(String serviceName) {
        log.info( serviceName + " is not reachable {}", dateFormat.format(new Date()));
        return null;
    }
}