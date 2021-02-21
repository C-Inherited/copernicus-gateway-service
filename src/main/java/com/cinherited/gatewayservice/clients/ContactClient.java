package com.cinherited.gatewayservice.clients;

import com.cinherited.gatewayservice.dtos.ContactDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("contact-service")
public interface ContactClient {

    /** GET **/
    @GetMapping("/contact/{id}")
    public ContactDTO getContact(@PathVariable Integer id);

    /** GET **/
    @GetMapping("/contact/")
    public List<ContactDTO> getAllContact();

    /** POST **/
    @PostMapping("/new/contact/")
    public ContactDTO postContact(@RequestBody @Valid ContactDTO contactDTO);

    /** PUT **/
    @PutMapping("contact/{id}")
    public ContactDTO putContact(@PathVariable Integer id,@RequestBody @Valid ContactDTO contactDTO);

    /** DELETE **/
    @DeleteMapping("contact/{id}")
    public void deleteContact(@PathVariable Integer id);
}
