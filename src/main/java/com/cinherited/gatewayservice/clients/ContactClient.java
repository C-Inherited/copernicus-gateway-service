package com.cinherited.gatewayservice.clients;

import com.cinherited.gatewayservice.dtos.AuthenticationRequest;
import com.cinherited.gatewayservice.dtos.ContactDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("contact-service")
public interface ContactClient {

    /** GET **/
    @GetMapping("/contact/{id}")
    ContactDTO getContact(@PathVariable Integer id, @RequestHeader(value = "Authorization") String authorizationHeader);

    /** GET **/
    @GetMapping("/contact/")
    List<ContactDTO> getAllContact(@RequestHeader(value = "Authorization") String authorizationHeader);

    /** POST **/
    @PostMapping("/new/contact/")
    ContactDTO postContact(@RequestBody @Valid ContactDTO contactDTO, @RequestHeader(value = "Authorization") String authorizationHeader);

    /** PUT **/
    @PutMapping("/contact/{id}")
    ContactDTO putContact(@PathVariable Integer id,@RequestBody @Valid ContactDTO contactDTO, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PostMapping("/contact/authenticate")
    ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest);
}
