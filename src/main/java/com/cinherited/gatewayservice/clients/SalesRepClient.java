package com.cinherited.gatewayservice.clients;

import com.cinherited.gatewayservice.dtos.AuthenticationRequest;
import com.cinherited.gatewayservice.dtos.CompleteSalesRepDTO;
import com.cinherited.gatewayservice.dtos.SalesRepDTO;
import com.cinherited.gatewayservice.dtos.SalesRepNameDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient("salesrep-service")
public interface SalesRepClient {

    @GetMapping("/salesrep/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompleteSalesRepDTO getSalesRepById(@PathVariable(name = "id") Integer id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PostMapping("/salesrep")
    @ResponseStatus(HttpStatus.CREATED)
    public SalesRepDTO saveSalesRep(@RequestBody @Valid SalesRepNameDTO name, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PutMapping("/salesrep/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public SalesRepDTO updateSalesRep(@RequestBody @Valid SalesRepNameDTO name, @PathVariable(name = "id") Integer id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @DeleteMapping("/salesrep/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSalesRep(@PathVariable(name = "id") Integer id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PostMapping("/salesrep/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest);

}
