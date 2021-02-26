package com.cinherited.gatewayservice.service.impl;

import com.cinherited.gatewayservice.clients.*;
import com.cinherited.gatewayservice.controllers.impl.AccountGatewayController;
import com.cinherited.gatewayservice.controllers.impl.ContactGatewayController;
import com.cinherited.gatewayservice.controllers.impl.OpportunityGatewayController;
import com.cinherited.gatewayservice.controllers.impl.SalesRepGatewayController;
import com.cinherited.gatewayservice.dtos.*;
import com.cinherited.gatewayservice.service.interfaces.ILeadsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class LeadsServices implements ILeadsServices {

    @Autowired
    private LeadClient leadClient;
    @Autowired
    private ContactClient contactClient;
    @Autowired
    private AccountClient accountClient;
    @Autowired
    private OpportunityClient opportunityClient;
    @Autowired
    private SalesRepClient salesRepClient;
    @Override
    public List<LeadDTO> findAll(String leadsAuthOk) {
        return leadClient.findAll(bearer + leadsAuthOk);
    }

    private String bearer = "Bearer ";

    private final CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();
    private CircuitBreaker leadCircuit = circuitBreakerFactory.create("lead-service");
    private CircuitBreaker contactCircuit = circuitBreakerFactory.create("contact-service");
    private CircuitBreaker opportunityCircuit = circuitBreakerFactory.create("opportunity-service");
    private CircuitBreaker accountCircuit = circuitBreakerFactory.create("account-service");
    private CircuitBreaker salesrepCircuit = circuitBreakerFactory.create("salesrep-service");


    @Override
    public List<LeadDTO> findAllBySalesRepId(String jwt, int salesRepId) {
        salesrepCircuit.run(() -> salesRepClient.getSalesRepById(salesRepId, bearer + SalesRepGatewayController.getSalesrepAuthOk()),
                throwable -> leadFallback("Salesrep"));
        return leadCircuit.run(() -> leadClient.findAllBySalesRepId( salesRepId, bearer + jwt), throwable -> (List<LeadDTO>) leadFallback("Lead"));
    }

    @Override
    public LeadDTO findByLeadId(String jwt, int leadId) {
        return leadCircuit.run(() -> leadClient.findByLeadId(leadId,bearer + jwt), throwable -> (LeadDTO) leadFallback("Lead"));
    }

    @Override
    public LeadDTO createNewLead(String jwt, LeadDTO leadDTO) {
        salesrepCircuit.run(() -> salesRepClient.getSalesRepById(leadDTO.getLeadSalesRepId(), bearer + SalesRepGatewayController.getSalesrepAuthOk()),
                throwable -> leadFallback("Salesrep"));
        return leadCircuit.run(() -> leadClient.createNewLead(leadDTO,bearer + jwt), throwable -> (LeadDTO) leadFallback("Lead"));
    }

    @Override
    public LeadDTO updateLead(String jwt, LeadDTO leadDTO) {
        salesrepCircuit.run(() -> salesRepClient.getSalesRepById(leadDTO.getLeadSalesRepId(), bearer + SalesRepGatewayController.getSalesrepAuthOk()),
                throwable -> leadFallback("Salesrep"));
        return leadCircuit.run(() -> leadClient.updateLead(leadDTO,bearer + jwt), throwable -> (LeadDTO) leadFallback("Lead"));
    }

    @Override
    public int deleteLead(String jwt, int leadId) {
        return leadCircuit.run(() -> leadClient.deleteLead(leadId,bearer + jwt), throwable -> (int) leadFallback("Lead"));
    }

    @Override
    public AccountDTO convertLead(String jwt, int leadId, Integer accountId, OpportunityDTO opportunityDTO) {
        LeadDTO leadDTO = leadCircuit.run(() -> leadClient.findByLeadId(leadId,bearer + jwt),
                throwable -> (LeadDTO) leadFallback("Lead"));

        AccountDTO accountDTO = accountCircuit.run(() -> accountClient.getAccount(accountId, bearer + AccountGatewayController.getAccountAuthOk()),
                throwable -> (AccountDTO) leadFallback("Account"));

        ContactDTO contactDTO = new ContactDTO(leadDTO.getLeadName(), leadDTO.getLeadPhone(), leadDTO.getLeadEmail(), leadDTO.getLeadCompanyName(), accountId);
        ContactDTO contactDTOValidated = contactCircuit.run(() -> contactClient.postContact(contactDTO, bearer+ContactGatewayController.getContactAuthOk()),
                throwable -> (ContactDTO) leadFallback("Contact"));

        opportunityDTO.setAccountId(accountDTO.getId());
        System.out.println(contactDTOValidated.getId());
        opportunityDTO.setContactId(contactDTOValidated.getId());

        opportunityDTO = opportunityClient.postOpportunity(opportunityDTO, bearer+OpportunityGatewayController.getOpportunityAuthOk());

        leadClient.deleteLead(leadId, bearer+ jwt);

        List<ContactDTO> accountContacts = accountDTO.getContacts();
        accountContacts.add(contactDTOValidated);
        accountDTO.setContacts(accountContacts);
        List<OpportunityDTO> opportunityContacts = accountDTO.getOpportunities();
        opportunityContacts.add(opportunityDTO);
        accountDTO.setOpportunities(opportunityContacts);


        return accountDTO;
    }

    private Object leadFallback(String service){
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, service+" service is not available right now");
    }
}
