package com.cinherited.gatewayservice.controllers.interfaces;

import com.cinherited.gatewayservice.dtos.ContactDTO;
import com.cinherited.gatewayservice.dtos.LeadDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface IGatewayController {

    List<LeadDTO> findAllLeads();

    /** CONTACT ROUTES **/
    public ContactDTO getContact(@PathVariable Integer id);
    public List<ContactDTO> getAllContact();
    public ContactDTO postContact(@RequestBody @Valid ContactDTO contactDTO);
    public ContactDTO putContact(@PathVariable Integer id,@RequestBody @Valid ContactDTO contactDTO);
    public void deleteContact(@PathVariable Integer id);
}
