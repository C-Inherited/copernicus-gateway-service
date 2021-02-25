package com.cinherited.gatewayservice.controllers.impl;

import com.cinherited.gatewayservice.clients.AccountClient;
import com.cinherited.gatewayservice.clients.ContactClient;
import com.cinherited.gatewayservice.controllers.interfaces.IContactGatewayController;
import com.cinherited.gatewayservice.dtos.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/copernicus")
public class ContactGatewayController implements IContactGatewayController {

    @Autowired
    private ContactClient contactClient;

    private static String contactAuthOk;

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

    public static String getContactAuthOk() {
        return contactAuthOk;
    }

    public static void setContactAuthOk(String contactAuthOk) {
        ContactGatewayController.contactAuthOk = contactAuthOk;
    }
}
