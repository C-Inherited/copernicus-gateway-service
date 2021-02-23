package com.cinherited.gatewayservice.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("opportunity-service")
public interface OpportunityClient {

}
