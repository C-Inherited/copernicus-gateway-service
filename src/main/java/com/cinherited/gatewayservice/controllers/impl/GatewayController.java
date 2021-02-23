package com.cinherited.gatewayservice.controllers.impl;

import com.cinherited.gatewayservice.clients.LeadClient;
import com.cinherited.gatewayservice.clients.StatsClient;
import com.cinherited.gatewayservice.controllers.interfaces.IGatewayController;
import com.cinherited.gatewayservice.dtos.LeadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/copernicus")
public class GatewayController implements IGatewayController {

    @Autowired
    private LeadClient leadClient;

    @Autowired
    private StatsClient statsClient;


    @Override
    @GetMapping("/leads/all")
    public List<LeadDTO> findAllLeads() {

        return leadClient.findAll();
    }

    @GetMapping("/account/avg/employee-count")
    public Double avgEmployeeCount(){
        return statsClient.avgEmployeeCount();
    }

    @GetMapping("/account/max/employee-count")
    public Integer maxEmployeeCount(){
        return statsClient.maxEmployeeCount();
    }

    @GetMapping("/account/min/employee-count")
    public Integer minEmployeeCount(){
        return statsClient.minEmployeeCount();
    }

    @GetMapping("/account/median/employee-count")
    public Double medianEmployeeCount(){
        return statsClient.medianEmployeeCount();
    }


    /** OPPORTUNITY STATS **/
    @GetMapping("/opportunity/count/by/product")
    public List<Object[]> countOpportunitiesByProduct(@RequestParam Optional<String> status){
        return statsClient.countOpportunitiesByProduct(status);
    }

    @GetMapping("/opportunity/count/by/city")
    public List<Object[]> countOpportunitiesByCity(@RequestParam Optional<String> status){
        return statsClient.countOpportunitiesByCity(status);
    }

    @GetMapping("/opportunity/count/by/country")
    public List<Object[]> countOpportunitiesByCountry(@RequestParam Optional<String> status){
        return statsClient.countOpportunitiesByCountry(status);
    }

    @GetMapping("/opportunity/count/by/industry")
    public List<Object[]> countOpportunitiesByIndustry(@RequestParam Optional<String> status){
        return statsClient.countOpportunitiesByIndustry(status);
    }

    @GetMapping("/opportunity/avg/by/account")
    public Double avgOpportunitiesByAccount(){
        return statsClient.avgOpportunitiesByAccount();
    }

    @GetMapping("/opportunity/max/by/account")
    public Integer maxOpportunitiesByAccount(){
        return statsClient.maxOpportunitiesByAccount();
    }

    @GetMapping("/opportunity/min/by/account")
    public Integer minOpportunitiesByAccount(){
        return statsClient.minOpportunitiesByAccount();
    }

    @GetMapping("/opportunity/median/by/account")
    public Double medianOpportunitiesByAccount(){
        return statsClient.medianOpportunitiesByAccount();
    }

    @GetMapping("/opportunity/avg/quantity/by/product")
    public List<Object[]> avgQuantityByProduct(){
        return statsClient.avgQuantityByProduct();
    }

    @GetMapping("/opportunity/max/quantity/by/product")
    public List<Object[]> maxQuantityByProduct(){
        return statsClient.maxQuantityByProduct();
    }

    @GetMapping("/opportunity/min/quantity/by/product")
    public List<Object[]> minQuantityByProduct(){
        return statsClient.minQuantityByProduct();
    }

    @GetMapping("/opportunity/median/quantity/by/product")
    public List<Object[]> medianQuantityByProduct(){
        return statsClient.medianQuantityByProduct();
    }

}
