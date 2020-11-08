package ru.mtuci.simpleapiiuk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mtuci.simpleapiiuk.model.Account;
import ru.mtuci.simpleapiiuk.model.Client;
import ru.mtuci.simpleapiiuk.service.AccountService;
import ru.mtuci.simpleapiiuk.service.ClientService;
import ru.mtuci.simpleapiiuk.service.DepositService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@Slf4j
@RestController
@RequestMapping(value = AccountController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    public static final String REST_URL = "/api/v1/accounts";

    private final AccountService accountService;
    private final ClientService clientService;
    private final DepositService depositService;

    @Autowired
    public AccountController(AccountService accountService, ClientService clientService, DepositService depositService) {
        this.accountService = accountService;
        this.clientService = clientService;
        this.depositService = depositService;
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

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@RequestBody @Valid Account account, @PathVariable Long id) {

        Client optionalClient = clientService.get(accountService.get(id).getClient().getId());

        account.setClient(optionalClient);
        account.setId(id);
        Account savedAccount = accountService.save(account);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAccount.getId()).toUri();
        return ResponseEntity.created(location).body(savedAccount);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        log.info("delete" + id);

        accountService.delete(id);
    }

    @PostMapping("/{id}") // condition: existing client
    public ResponseEntity<Account> create(@RequestBody @Valid Account account, @PathVariable Long id) {

        Client optionalClient = clientService.get(id);
        account.setClient(optionalClient);
        Account savedAccount = accountService.save(account);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAccount.getId()).toUri();
        return ResponseEntity.created(location).body(savedAccount);
    }
}