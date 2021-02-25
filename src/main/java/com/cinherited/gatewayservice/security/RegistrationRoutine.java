package com.cinherited.gatewayservice.security;


import com.cinherited.gatewayservice.clients.LeadClient;
import com.cinherited.gatewayservice.controllers.impl.GatewayController;
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

    public static boolean isLeadRegistered = false;

    private static final Logger log = LoggerFactory.getLogger(RegistrationRoutine.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

        @Scheduled(fixedRate = 10000)
    public void checkRegistration() {
            if (!isLeadRegistered){
                CircuitBreaker circuitBreaker = circuitBreakerFactory.create("leads-service");
                log.info("Trying to register with leads-service {}", dateFormat.format(new Date()));
                AuthenticationRequest authenticationRequest = new AuthenticationRequest("gateway", "gateway");
                ResponseEntity<?> responseEntity= circuitBreaker.run(() -> leadClient.createAuthenticationToken(authenticationRequest), throwable -> fallbackTransaction("leads-service"));
                if (responseEntity != null) {
                    parseJWT(responseEntity);
                    isLeadRegistered = true;
                    log.info("Registered with leads-service auth token: {}", GatewayController.getLeadsAuthOk());
                }
            }
    }

    private void parseJWT(ResponseEntity<?> responseEntity) {
        String auth = Objects.requireNonNull(responseEntity.getBody()).toString();
        GatewayController.setLeadsAuthOk(auth.substring(5, auth.length() - 1));
    }

    private ResponseEntity<?> fallbackTransaction(String serviceName) {
        log.info( serviceName + " is not reachable {}", dateFormat.format(new Date()));
        return null;
    }
}