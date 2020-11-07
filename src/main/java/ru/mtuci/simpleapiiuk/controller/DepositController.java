package ru.mtuci.simpleapiiuk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.simpleapiiuk.model.Deposit;
import ru.mtuci.simpleapiiuk.service.DepositService;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = DepositController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DepositController {
    public static final String REST_URL = "/api/v1/deposits";

    private final DepositService depositService;

    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @GetMapping(value = "/{id}")
    public Deposit get(@PathVariable("id") Long id) {
        log.info("get" + id);
        return depositService.get(id);
    }

    @GetMapping
    public List<Deposit> getAll() {
        log.info("getAll");
        return depositService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Deposit save(@RequestBody Deposit deposit){
        log.info("save" + deposit);
        return depositService.save(deposit);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        log.info("delete" + id);
        depositService.delete(id);
    }
}