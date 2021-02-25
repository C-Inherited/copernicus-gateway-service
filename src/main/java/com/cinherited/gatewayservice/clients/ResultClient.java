package com.cinherited.gatewayservice.clients;

import com.cinherited.gatewayservice.dtos.AuthenticationRequest;
import com.cinherited.gatewayservice.dtos.RequestDTO;
import com.cinherited.gatewayservice.enums.Status;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("result-service")
public interface ResultClient {
    @GetMapping("/opportunity-status/{id}")
    Status getResult(Integer id, @RequestHeader(value = "Authorization") String authorizationHeader);
    @PostMapping("/opportunity-status/{id}")
    Integer changeStatus(RequestDTO requestDTO, @RequestHeader(value = "Authorization") String authorizationHeader);
    /** AUTHENTICATION **/
    @PostMapping("/status/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest);
}
