package com.cinherited.gatewayservice.clients;

import com.cinherited.gatewayservice.dtos.AccountDTO;
import com.cinherited.gatewayservice.dtos.AuthenticationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("account-service")
public interface AccountClient {

    @GetMapping("/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO getAccount(@PathVariable(name = "id") Integer id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDTO> getAllAccounts(@RequestHeader(value = "Authorization") String authorizationHeader);

    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO saveAccount(@RequestBody @Valid AccountDTO accountDTO, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PutMapping("/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO updateAccount(@RequestBody @Valid AccountDTO accountDTO, @PathVariable(name = "id") Integer id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @DeleteMapping("/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@PathVariable(name = "id") Integer id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PostMapping("/account/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest);
}
