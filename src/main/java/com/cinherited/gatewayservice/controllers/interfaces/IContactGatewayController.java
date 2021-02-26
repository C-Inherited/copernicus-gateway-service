package com.cinherited.gatewayservice.controllers.interfaces;

import com.cinherited.gatewayservice.dtos.ContactDTO;

import java.util.List;

public interface IContactGatewayController {

    /** CONTACT ROUTES **/
    ContactDTO getContact(Integer id);
    List<ContactDTO> getAllContact();
    ContactDTO putContact(Integer id,ContactDTO contactDTO);
}
