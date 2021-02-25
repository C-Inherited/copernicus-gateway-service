package com.cinherited.gatewayservice.controllers.impl;

import com.cinherited.gatewayservice.clients.SalesRepClient;
import com.cinherited.gatewayservice.dtos.CompleteSalesRepDTO;
import com.cinherited.gatewayservice.dtos.SalesRepDTO;
import com.cinherited.gatewayservice.dtos.SalesRepNameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SalesRepGatewayController {


    @Autowired
    private SalesRepClient salesRepClient;

    @GetMapping("/salesrep/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompleteSalesRepDTO getSalesRepById(@PathVariable(name = "id") Integer id) {
        return salesRepClient.getSalesRepById(id);
    }

    @PostMapping("/salesrep")
    @ResponseStatus(HttpStatus.CREATED)
    public SalesRepDTO saveSalesRep(@RequestBody @Valid SalesRepNameDTO name) {
        return salesRepClient.saveSalesRep(name);
    }

    @PutMapping("/salesrep/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public SalesRepDTO updateSalesRep(@RequestBody @Valid SalesRepNameDTO name, @PathVariable(name = "id") Integer id) {
        return salesRepClient.updateSalesRep(name, id);
    }

    @DeleteMapping("/salesrep/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSalesRep(@PathVariable(name = "id") Integer id) {
        salesRepClient.deleteSalesRep(id);
    }
}
