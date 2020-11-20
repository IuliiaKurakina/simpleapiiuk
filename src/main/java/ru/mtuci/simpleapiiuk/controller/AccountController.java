package ru.mtuci.simpleapiiuk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.simpleapiiuk.model.Account;
import ru.mtuci.simpleapiiuk.model.Client;
import ru.mtuci.simpleapiiuk.service.AccountService;
import ru.mtuci.simpleapiiuk.service.ClientService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping(value = AccountController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    public static final String REST_URL = "/api/v1/accounts";

    private final AccountService accountService;
    private final ClientService clientService;

    @Autowired
    public AccountController(AccountService accountService, ClientService clientService) {
        this.accountService = accountService;
        this.clientService = clientService;
    }

    @GetMapping(value = "/{id}")
    public Account get(@PathVariable("id") Long id) {
        log.info("get" + id);
        return accountService.get(id);
    }

    @GetMapping
    public List<Account> getAll() {
        log.info("getAll");
        return accountService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        log.info("delete" + id);

        accountService.delete(id);
    }

    @PostMapping()
    public ResponseEntity<Account> create(@RequestBody @Valid Account account) {
        Account savedAccount = accountService.save(account);

        return ResponseEntity.of(Optional.of(savedAccount));
    }

    @PostMapping("/setClientToAccount")
    public ResponseEntity<Boolean> setClientToAccount(Long clientId, Long accountId) {
        Client client = clientService.get(clientId);
        Account account = accountService.get(accountId);

        if (client == null || account == null) {
            return ResponseEntity.of(Optional.of(false));
        }

        account.setClient(client);
        accountService.save(account);

        return ResponseEntity.of(Optional.of(true));
    }
}