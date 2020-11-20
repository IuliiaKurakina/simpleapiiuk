package ru.mtuci.simpleapiiuk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.simpleapiiuk.model.Account;
import ru.mtuci.simpleapiiuk.model.Client;
import ru.mtuci.simpleapiiuk.model.Deposit;
import ru.mtuci.simpleapiiuk.service.AccountService;
import ru.mtuci.simpleapiiuk.service.DepositService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = DepositController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DepositController {
    public static final String REST_URL = "/api/v1/deposits";

    private final DepositService depositService;
    private final AccountService accountService;


    @Autowired
    public DepositController(DepositService depositService, AccountService accountService) {
        this.depositService = depositService;
        this.accountService = accountService;
    }

    @PostMapping()
    public ResponseEntity<Deposit> create(@RequestBody @Valid Deposit deposit) {
        Deposit savedDeposit = depositService.save(deposit);

        return ResponseEntity.of(Optional.of(savedDeposit));
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

    @PostMapping("/setAccountToDeposit")
    public ResponseEntity<Boolean> setAccountToDeposit(Long depositId, Long accountId) {
        Deposit deposit = depositService.get(depositId);
        Account account = accountService.get(accountId);

        if (deposit == null || account == null) {
            return ResponseEntity.of(Optional.of(false));
        }

        deposit.setAccount(account);
        depositService.save(deposit);

        return ResponseEntity.of(Optional.of(true));
    }
}