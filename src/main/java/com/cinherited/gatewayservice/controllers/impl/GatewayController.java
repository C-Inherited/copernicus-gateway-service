package com.cinherited.gatewayservice.controllers.impl;

import com.cinherited.gatewayservice.clients.ContactClient;
import com.cinherited.gatewayservice.clients.LeadClient;
import com.cinherited.gatewayservice.controllers.interfaces.IGatewayController;
import com.cinherited.gatewayservice.dtos.ContactDTO;
import com.cinherited.gatewayservice.dtos.LeadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/copernicus")
public class GatewayController implements IGatewayController {

    @Autowired
    private LeadClient leadClient;

    @Autowired
    private ContactClient contactClient;

    @Override
    @GetMapping("/leads/all")
    public List<LeadDTO> findAllLeads() {

        return leadClient.findAll();
    }

    /** CONTACT **/
    /** GET **/
    @GetMapping("/contact/{id}")
    public ContactDTO getContact(@PathVariable Integer id, @RequestHeader(value = "Authorization") String authorizationHeader){
       return contactClient.getContact(id, authorizationHeader);
    }

    /** GET **/
    @GetMapping("/contact/")
    public List<ContactDTO> getAllContact(@RequestHeader(value = "Authorization") String authorizationHeader){
        return contactClient.getAllContact(authorizationHeader);
    }

    /** POST **/
    @PostMapping("/new/contact/")
    public ContactDTO postContact(@RequestBody @Valid ContactDTO contactDTO, @RequestHeader(value = "Authorization") String authorizationHeader){
        return contactClient.postContact(contactDTO, authorizationHeader);
    }

    /** PUT **/
    @PutMapping("/contact/{id}")
    public ContactDTO putContact(@PathVariable Integer id,@RequestBody @Valid ContactDTO contactDTO, @RequestHeader(value = "Authorization") String authorizationHeader){
        return contactClient.putContact(id, contactDTO, authorizationHeader);
    }

    /** DELETE **/
    @DeleteMapping("/contact/{id}")
    public void deleteContact(@PathVariable Integer id, @RequestHeader(value = "Authorization") String authorizationHeader){
        contactClient.deleteContact(id, authorizationHeader);
    }


}
