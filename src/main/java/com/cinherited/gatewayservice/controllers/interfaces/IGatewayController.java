package com.cinherited.gatewayservice.controllers.interfaces;

import com.cinherited.gatewayservice.dtos.ContactDTO;
import com.cinherited.gatewayservice.dtos.LeadDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;
import java.util.List;

public interface IGatewayController {

    List<LeadDTO> findAllLeads();

    /** CONTACT ROUTES **/
    public ContactDTO getContact(@PathVariable Integer id, @RequestHeader(value = "Authorization") String authorizationHeader);
    public List<ContactDTO> getAllContact(@RequestHeader(value = "Authorization") String authorizationHeader);
    public ContactDTO postContact(@RequestBody @Valid ContactDTO contactDTO, @RequestHeader(value = "Authorization") String authorizationHeader);
    public ContactDTO putContact(@PathVariable Integer id,@RequestBody @Valid ContactDTO contactDTO, @RequestHeader(value = "Authorization") String authorizationHeader);
    public void deleteContact(@PathVariable Integer id, @RequestHeader(value = "Authorization") String authorizationHeader);
}
