package com.cinherited.gatewayservice.controllers.interfaces;

import com.cinherited.gatewayservice.dtos.ContactDTO;
import com.cinherited.gatewayservice.dtos.LeadDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface IContactGatewayController {

    /** CONTACT ROUTES **/
    ContactDTO getContact(Integer id);
    List<ContactDTO> getAllContact();
    ContactDTO postContact(ContactDTO contactDTO);
    ContactDTO putContact(Integer id,ContactDTO contactDTO);
}
