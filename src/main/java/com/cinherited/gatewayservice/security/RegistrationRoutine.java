package com.cinherited.gatewayservice.security;

import com.cinherited.gatewayservice.clients.OpportunityClient;
import com.cinherited.gatewayservice.clients.StatsClient;
import com.cinherited.gatewayservice.controllers.impl.*;
import com.cinherited.gatewayservice.clients.*;
import com.cinherited.gatewayservice.dtos.AuthenticationRequest;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class RegistrationRoutine {
    @Autowired
    LeadClient leadClient;
    @Autowired
    AccountClient accountClient;
    @Autowired
    SalesRepClient salesRepClient;
    @Autowired
    OpportunityClient opportunityClient;
    @Autowired
    StatsClient statsClient;
    @Autowired
    ContactClient contactClient;

    public static boolean isLeadRegistered = false;
    public static boolean isAccountRegistered = false;
    public static boolean isSalesRepRegistered = false;
    public static boolean isOpportunityRegistered = false;
    public static boolean isStatsRegistered = false;
    public static boolean isContactRegistered = false;


    private static final Logger log = LoggerFactory.getLogger(RegistrationRoutine.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    @Scheduled(fixedRate = 10000)
    public void checkLeadRegistration() {
        if (!isLeadRegistered) {
            CircuitBreaker circuitBreaker = circuitBreakerFactory.create("leads-service");
            log.info("Trying to register with leads-service {}", dateFormat.format(new Date()));
            AuthenticationRequest authenticationRequest = new AuthenticationRequest("gateway", "gateway");
            ResponseEntity<?> responseEntity = circuitBreaker.run(() -> leadClient.createAuthenticationToken(authenticationRequest), throwable -> fallbackTransaction("leads-service"));
            if (responseEntity != null) {
                parseJWT(responseEntity);
                isLeadRegistered = true;
                log.info("Registered with leads-service auth token: {}", GatewayController.getLeadsAuthOk());
            }
        }
    }

    @Scheduled(fixedRate = 10000)
    public void checkOpportunityRegistration() {
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
    public void checkAccountRegistration() {
        if (!isAccountRegistered) {
            CircuitBreaker circuitBreaker = circuitBreakerFactory.create("account-service");
            log.info("Trying to register with account-service {}", dateFormat.format(new Date()));
            AuthenticationRequest authenticationRequest = new AuthenticationRequest("gateway", "gateway");
            ResponseEntity<?> responseEntity = circuitBreaker.run(() -> accountClient.createAuthenticationToken(authenticationRequest), throwable -> fallbackTransaction("account-service"));
            if (responseEntity != null) {
                parseAccountJWT(responseEntity);
                isAccountRegistered = true;
                log.info("Registered with account-service auth token: {}", AccountGatewayController.getAccountAuthOk());
            }
        }
    }

    @Scheduled(fixedRate = 10000)
    public void checkSalesrepRegistration() {
        if (!isSalesRepRegistered) {
            CircuitBreaker circuitBreaker = circuitBreakerFactory.create("salesrep-service");
            log.info("Trying to register with salesrep-service {}", dateFormat.format(new Date()));
            AuthenticationRequest authenticationRequest = new AuthenticationRequest("gateway", "gateway");
            ResponseEntity<?> responseEntity = circuitBreaker.run(() -> salesRepClient.createAuthenticationToken(authenticationRequest), throwable -> fallbackTransaction("salesrep-service"));
            if (responseEntity != null) {
                parseSalesRepJWT(responseEntity);
                isSalesRepRegistered = true;
                log.info("Registered with salesrep-service auth token: {}", SalesRepGatewayController.getSalesrepAuthOk());
            }
        }
    }

    @Scheduled(fixedRate = 10000)
    public void checkStatsRegistration() {
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

    @Scheduled(fixedRate = 10000)
    public void checkContactRegistration() {
        if (!isContactRegistered){
            CircuitBreaker circuitBreaker = circuitBreakerFactory.create("contact-service");
            log.info("Trying to register with contact-service {}", dateFormat.format(new Date()));
            AuthenticationRequest authenticationRequest = new AuthenticationRequest("gateway", "gateway");
            ResponseEntity<?> responseEntity= circuitBreaker.run(() -> contactClient.createAuthenticationToken(authenticationRequest), throwable -> fallbackTransaction("contact-service"));
            if (responseEntity != null) {
                parseJWTContact(responseEntity);
                isContactRegistered = true;
                log.info("Registered with contact-service auth token: {}", ContactGatewayController.getContactAuthOk());
            }
        }
    }


    private void parseJWT(ResponseEntity<?> responseEntity) {
        String auth = Objects.requireNonNull(responseEntity.getBody()).toString();
        GatewayController.setLeadsAuthOk(auth.substring(5, auth.length() - 1));
        }

    private void parseAccountJWT(ResponseEntity<?> responseEntity) {
        String auth = Objects.requireNonNull(responseEntity.getBody()).toString();
        AccountGatewayController.setAccountAuthOk(auth.substring(5, auth.length() - 1));
        }

    private void parseSalesRepJWT(ResponseEntity<?> responseEntity) {
        String auth = Objects.requireNonNull(responseEntity.getBody()).toString();
        SalesRepGatewayController.setSalesrepAuthOk(auth.substring(5, auth.length() - 1));
        }

    private void parseJWTOpportunity(ResponseEntity<?> responseEntity) {
        String auth = Objects.requireNonNull(responseEntity.getBody()).toString();
        OpportunityGatewayController.setOpportunityAuthOk(auth.substring(5, auth.length() - 1));
        }

    private void parseJWTStats(ResponseEntity<?> responseEntity) {
        String auth = Objects.requireNonNull(responseEntity.getBody()).toString();
        StatsGatewayController.setStatsAuthOk(auth.substring(5, auth.length() - 1));
    }

    private void parseJWTContact(ResponseEntity<?> responseEntity) {
        String auth = Objects.requireNonNull(responseEntity.getBody()).toString();
        ContactGatewayController.setContactAuthOk(auth.substring(5, auth.length() - 1));
    }

    private ResponseEntity<?> fallbackTransaction(String serviceName) {
        log.info(serviceName + " is not reachable {}", dateFormat.format(new Date()));
        return null;
        }
}

