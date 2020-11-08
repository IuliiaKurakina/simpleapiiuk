package ru.mtuci.simpleapiiuk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mtuci.simpleapiiuk.model.Account;
import ru.mtuci.simpleapiiuk.model.Deposit;
import ru.mtuci.simpleapiiuk.service.AccountService;
import ru.mtuci.simpleapiiuk.service.ClientService;
import ru.mtuci.simpleapiiuk.service.DepositService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = DepositController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DepositController {
    public static final String REST_URL = "/api/v1/deposits";

    private final DepositService depositService;
    private final AccountService accountService;
    private final ClientService clientService;

    @Autowired
    public DepositController(DepositService depositService, AccountService accountService, ClientService clientService) {
        this.depositService = depositService;
        this.accountService = accountService;
        this.clientService = clientService;
    }

    @PostMapping("/{id}") // condition: existing client
    public ResponseEntity<Deposit> create(@RequestBody @Valid Deposit deposit, @PathVariable Long id) {

        Account optionalAccount = accountService.get(id);
        deposit.setAccount(optionalAccount);
        Deposit savedDeposit = depositService.save(deposit);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedDeposit.getId()).toUri();
        return ResponseEntity.created(location).body(savedDeposit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Deposit> update(@RequestBody @Valid Deposit deposit, @PathVariable Long id) {

        Account optionalAccount = accountService.get(depositService.get(id).getAccount().getId());

        deposit.setAccount(optionalAccount);
        deposit.setId(id);
        Deposit savedDeposit = depositService.save(deposit);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedDeposit.getId()).toUri();
        return ResponseEntity.created(location).body(savedDeposit);
    }

    @GetMapping
    public List<Deposit> getAll() {
        log.info("getAll");
        return depositService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        log.info("delete" + id);
        depositService.delete(id);
    }

    @GetMapping(value = "/{id}")
    public Deposit get(@PathVariable("id") Long id) {
        log.info("get" + id);
        return depositService.get(id);
    }
}