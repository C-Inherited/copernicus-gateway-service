package com.cinherited.gatewayservice.controllers.impl;

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
    public ContactDTO getContact(@PathVariable Integer id){
        return contactClient.getContact(id, "Bearer " + getContactAuthOk());
    }

    /** GET **/
    @GetMapping("/contact/")
    public List<ContactDTO> getAllContact(){
        return contactClient.getAllContact( "Bearer " + getContactAuthOk());
    }


    /** PUT **/
    @PutMapping("/contact/{id}")
    public ContactDTO putContact(@PathVariable Integer id,@RequestBody @Valid ContactDTO contactDTO){
        return contactClient.putContact(id, contactDTO, "Bearer " + getContactAuthOk());
    }


    public static String getContactAuthOk() {
        return contactAuthOk;
    }

    public static void setContactAuthOk(String contactAuthOk) {
        ContactGatewayController.contactAuthOk = contactAuthOk;
    }
}
