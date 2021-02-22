package com.cinherited.gatewayservice.clients;

import com.cinherited.gatewayservice.dtos.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("account-service")
public interface AccountClient {

    @GetMapping("/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO getAccount(@PathVariable(name = "id") Integer id);

    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDTO> getAllAccounts();

    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO saveAccount(@RequestBody @Valid AccountDTO accountDTO);

    @PutMapping("/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO updateAccount(@RequestBody @Valid AccountDTO accountDTO, @PathVariable (name = "id") Integer id);

    @DeleteMapping("/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@PathVariable (name = "id") Integer id);
}
