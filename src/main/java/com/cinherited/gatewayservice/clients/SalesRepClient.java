package com.cinherited.gatewayservice.clients;

import com.cinherited.gatewayservice.dtos.CompleteSalesRepDTO;
import com.cinherited.gatewayservice.dtos.SalesRepDTO;
import com.cinherited.gatewayservice.dtos.SalesRepNameDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient("salesrep-service")
public interface SalesRepClient {

    @GetMapping("/salesrep/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompleteSalesRepDTO getSalesRepById(@PathVariable(name = "id") Integer id);

    @PostMapping("/salesrep")
    @ResponseStatus(HttpStatus.CREATED)
    public SalesRepDTO saveSalesRep(@RequestBody @Valid SalesRepNameDTO name);

    @PutMapping("/salesrep/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public SalesRepDTO updateSalesRep(@RequestBody @Valid SalesRepNameDTO name, @PathVariable(name = "id") Integer id);

    @DeleteMapping("/salesrep/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSalesRep(@PathVariable(name = "id") Integer id);

}
