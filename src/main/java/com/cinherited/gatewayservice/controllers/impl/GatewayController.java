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
    public ContactDTO getContact(@PathVariable Integer id){
       return contactClient.getContact(id);
    };

    /** GET **/
    @GetMapping("/contact/")
    public List<ContactDTO> getAllContact(){
        return contactClient.getAllContact();
    };

    /** POST **/
    @PostMapping("/new/contact/")
    public ContactDTO postContact(@RequestBody @Valid ContactDTO contactDTO){
        return contactClient.postContact(contactDTO);
    };

    /** PUT **/
    @PutMapping("contact/{id}")
    public ContactDTO putContact(@PathVariable Integer id,@RequestBody @Valid ContactDTO contactDTO){
        return contactClient.putContact(id, contactDTO);
    };

    /** DELETE **/
    @DeleteMapping("contact/{id}")
    public void deleteContact(@PathVariable Integer id){
        contactClient.deleteContact(id);
    };


}
