package com.cinherited.gatewayservice.controllers.impl;

import com.cinherited.gatewayservice.clients.AccountClient;
import com.cinherited.gatewayservice.dtos.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountGatewayController {

    @Autowired
    private AccountClient accountClient;

    private static String accountAuthOk;

    @GetMapping("/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO getAccount(@PathVariable(name = "id") Integer id) {
        return accountClient.getAccount(id, "Bearer " + accountAuthOk);
    }

    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDTO> getAllAccounts() {
        return accountClient.getAllAccounts("Bearer " + accountAuthOk);
    }

    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO saveAccount(@RequestBody @Valid AccountDTO accountDTO) {
        return accountClient.saveAccount(accountDTO, "Bearer " + accountAuthOk);
    }

    @PutMapping("/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO updateAccount(@RequestBody @Valid AccountDTO accountDTO, @PathVariable(name = "id") Integer id) {
        return accountClient.updateAccount(accountDTO, id, "Bearer " + accountAuthOk);
    }

    @DeleteMapping("/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@PathVariable(name = "id") Integer id) {
        accountClient.deleteAccount(id, "Bearer " + accountAuthOk);
    }

    public static String getAccountAuthOk() {
        return accountAuthOk;
    }

    public static void setAccountAuthOk(String accountAuthOk) {
        AccountGatewayController.accountAuthOk = accountAuthOk;
    }
}
