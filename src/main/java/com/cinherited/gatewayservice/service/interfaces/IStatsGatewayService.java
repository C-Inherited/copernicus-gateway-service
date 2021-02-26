package com.cinherited.gatewayservice.service.interfaces;

import java.util.List;
import java.util.Optional;

public interface IStatsGatewayService {

    /** ACCOUNT STATS **/
    Double avgEmployeeCount();

    Integer maxEmployeeCount();

    Integer minEmployeeCount();

    Double medianEmployeeCount();


    /** OPPORTUNITY STATS **/
    List<Object[]> countOpportunitiesByProduct(Optional<String> status);

    List<Object[]> countOpportunitiesByCity(Optional<String> status);

    List<Object[]> countOpportunitiesByCountry(Optional<String> status);

    List<Object[]> countOpportunitiesByIndustry(Optional<String> status);

    Double avgOpportunitiesByAccount();

    Integer maxOpportunitiesByAccount();

    Integer minOpportunitiesByAccount();

    Double medianOpportunitiesByAccount();

    List<Object[]> avgQuantityByProduct();

    List<Object[]> maxQuantityByProduct();

    List<Object[]> minQuantityByProduct();

    List<Object[]> medianQuantityByProduct();
}
