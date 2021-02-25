package com.cinherited.gatewayservice.controllers.impl;

import com.cinherited.gatewayservice.clients.StatsClient;
import com.cinherited.gatewayservice.controllers.interfaces.IStatsGatewayController;
import com.cinherited.gatewayservice.dtos.AuthenticationRequest;
import com.cinherited.gatewayservice.security.MyUserDetailsService;
import com.cinherited.gatewayservice.service.interfaces.IStatsGatewayService;
import com.cinherited.gatewayservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/copernicus")
public class StatsGatewayController implements IStatsGatewayController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    private static String statsAuthOk;

    @Autowired
    IStatsGatewayService statsGatewayService;

    @GetMapping("/account/avg/employee-count")
    public Double avgEmployeeCount(){
        return statsGatewayService.avgEmployeeCount();
    }

    @GetMapping("/account/max/employee-count")
    public Integer maxEmployeeCount(){
        return statsGatewayService.maxEmployeeCount();
    }

    @GetMapping("/account/min/employee-count")
    public Integer minEmployeeCount(){
        return statsGatewayService.minEmployeeCount();
    }

    @GetMapping("/account/median/employee-count")
    public Double medianEmployeeCount(){
        return statsGatewayService.medianEmployeeCount();
    }


    /** OPPORTUNITY STATS **/
    @GetMapping("/opportunity/count/by/product")
    public List<Object[]> countOpportunitiesByProduct(@RequestParam Optional<String> status){
        return statsGatewayService.countOpportunitiesByProduct(status);
    }

    @GetMapping("/opportunity/count/by/city")
    public List<Object[]> countOpportunitiesByCity(@RequestParam Optional<String> status){
        return statsGatewayService.countOpportunitiesByCity(status);
    }

    @GetMapping("/opportunity/count/by/country")
    public List<Object[]> countOpportunitiesByCountry(@RequestParam Optional<String> status){
        return statsGatewayService.countOpportunitiesByCountry(status);
    }

    @GetMapping("/opportunity/count/by/industry")
    public List<Object[]> countOpportunitiesByIndustry(@RequestParam Optional<String> status){
        return statsGatewayService.countOpportunitiesByIndustry(status);
    }

    @GetMapping("/opportunity/avg/by/account")
    public Double avgOpportunitiesByAccount(){
        return statsGatewayService.avgOpportunitiesByAccount();
    }

    @GetMapping("/opportunity/max/by/account")
    public Integer maxOpportunitiesByAccount(){
        return statsGatewayService.maxOpportunitiesByAccount();
    }

    @GetMapping("/opportunity/min/by/account")
    public Integer minOpportunitiesByAccount(){
        return statsGatewayService.minOpportunitiesByAccount();
    }

    @GetMapping("/opportunity/median/by/account")
    public Double medianOpportunitiesByAccount(){
        return statsGatewayService.medianOpportunitiesByAccount();
    }

    @GetMapping("/opportunity/avg/quantity/by/product")
    public List<Object[]> avgQuantityByProduct(){
        return statsGatewayService.avgQuantityByProduct();
    }

    @GetMapping("/opportunity/max/quantity/by/product")
    public List<Object[]> maxQuantityByProduct(){
        return statsGatewayService.maxQuantityByProduct();
    }

    @GetMapping("/opportunity/min/quantity/by/product")
    public List<Object[]> minQuantityByProduct(){
        return statsGatewayService.minQuantityByProduct();
    }

    @GetMapping("/opportunity/median/quantity/by/product")
    public List<Object[]> medianQuantityByProduct(){
        return statsGatewayService.medianQuantityByProduct();
    }

    public static String getStatsAuthOk() {
        return statsAuthOk;
    }

    public static void setStatsAuthOk(String statsAuthOk) {
        StatsGatewayController.statsAuthOk = statsAuthOk;
    }

}
