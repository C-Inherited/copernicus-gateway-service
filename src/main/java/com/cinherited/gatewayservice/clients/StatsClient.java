package com.cinherited.gatewayservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@FeignClient("stats-client")
public interface StatsClient {

    /** ACCOUNT STATS **/
    @GetMapping("/account/avg/employee-count")
    Double avgEmployeeCount();

    @GetMapping("/account/max/employee-count")
    Integer maxEmployeeCount();

    @GetMapping("/account/min/employee-count")
    Integer minEmployeeCount();

    @GetMapping("/account/median/employee-count")
    Double medianEmployeeCount();


    /** OPPORTUNITY STATS **/
    @GetMapping("/opportunity/count/by/product")
    public List<Object[]> countOpportunitiesByProduct(Optional<String> status);

    @GetMapping("/opportunity/count/by/city")
    public List<Object[]> countOpportunitiesByCity(Optional<String> status);

    @GetMapping("/opportunity/count/by/country")
    public List<Object[]> countOpportunitiesByCountry(Optional<String> status);

    @GetMapping("/opportunity/count/by/industry")
    public List<Object[]> countOpportunitiesByIndustry(Optional<String> status);

    @GetMapping("/opportunity/avg/by/account")
    public Double avgOpportunitiesByAccount();

    @GetMapping("/opportunity/max/by/account")
    public Integer maxOpportunitiesByAccount();

    @GetMapping("/opportunity/min/by/account")
    public Integer minOpportunitiesByAccount();

    @GetMapping("/opportunity/median/by/account")
    public Double medianOpportunitiesByAccount();

    @GetMapping("/opportunity/avg/quantity/by/product")
    public List<Object[]> avgQuantityByProduct();

    @GetMapping("/opportunity/max/quantity/by/product")
    public List<Object[]> maxQuantityByProduct();

    @GetMapping("/opportunity/min/quantity/by/product")
    public List<Object[]> minQuantityByProduct();

    @GetMapping("/opportunity/median/quantity/by/product")
    public List<Object[]> medianQuantityByProduct();
}
