package com.cinherited.gatewayservice.clients;

import com.cinherited.gatewayservice.dtos.AuthenticationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient("stats-service")
public interface StatsClient {

    /** ACCOUNT STATS **/
    @GetMapping("/account/avg/employee-count")
    Double avgEmployeeCount(@RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/account/max/employee-count")
    Integer maxEmployeeCount(@RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/account/min/employee-count")
    Integer minEmployeeCount(@RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/account/median/employee-count")
    Double medianEmployeeCount(@RequestHeader(value = "Authorization") String authorizationHeader);


    /** OPPORTUNITY STATS **/
    @GetMapping("/opportunity/count/by/product")
    List<Object[]> countOpportunitiesByProduct(Optional<String> status, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/opportunity/count/by/city")
    List<Object[]> countOpportunitiesByCity(Optional<String> status, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/opportunity/count/by/country")
    List<Object[]> countOpportunitiesByCountry(Optional<String> status, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/opportunity/count/by/industry")
    List<Object[]> countOpportunitiesByIndustry(Optional<String> status, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/opportunity/avg/by/account")
    Double avgOpportunitiesByAccount(@RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/opportunity/max/by/account")
    Integer maxOpportunitiesByAccount(@RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/opportunity/min/by/account")
    Integer minOpportunitiesByAccount(@RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/opportunity/median/by/account")
    Double medianOpportunitiesByAccount(@RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/opportunity/avg/quantity/by/product")
    List<Object[]> avgQuantityByProduct(@RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/opportunity/max/quantity/by/product")
    List<Object[]> maxQuantityByProduct(@RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/opportunity/min/quantity/by/product")
    List<Object[]> minQuantityByProduct(@RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/opportunity/median/quantity/by/product")
    List<Object[]> medianQuantityByProduct(@RequestHeader(value = "Authorization") String authorizationHeader);

    @RequestMapping(value = "stats/authenticate", method = RequestMethod.POST)
    ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest);

}
