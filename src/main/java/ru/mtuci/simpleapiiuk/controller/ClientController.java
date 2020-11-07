package ru.mtuci.simpleapiiuk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.simpleapiiuk.model.Client;
import ru.mtuci.simpleapiiuk.service.AccountService;
import ru.mtuci.simpleapiiuk.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import javax.validation.Valid;
import java.net.URI;


@Slf4j
@RestController
@RequestMapping(value = ClientController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    public static final String REST_URL = "/api/v1/clients";

    private final ClientService clientService;
    private final AccountService accountService;

    @Autowired
    public ClientController(ClientService clientService, AccountService accountService) {
        this.clientService = clientService;
        this.accountService = accountService;
    }

    @GetMapping(value = "/{id}")
    public Client get(@PathVariable("id") Long id) {
        log.info("get" + id);
        return clientService.get(id);
    }

    @GetMapping
    public List<Client> getAll() {
        log.info("getAll");
        return clientService.getAll();
    }
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Client save(@RequestBody Client client) {
//        log.info("save" + client);
//        return clientService.save(client);
//    }

    @PostMapping
    public ResponseEntity<Client> create(@Valid @RequestBody Client client) {
        Client savedClient = clientService.save(client);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedClient.getId()).toUri();

        return ResponseEntity.created(location).body(savedClient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        log.info("delete" + id);
        clientService.delete(id);
    }

//    private final ClientRepository clientRepository;
//    private final AccountRepository accountRepository;
//
//    @Autowired
//    public ClientController(ClientRepository clientRepository, AccountRepository accountRepository) {
//        this.clientRepository = clientRepository;
//        this.accountRepository = accountRepository;
//    }
//    @PostMapping
//    public ResponseEntity<Client> create(@Valid @RequestBody Client client) {
//        Client savedClient = ClientRepository.save(client);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(savedClient.getId()).toUri();
//        return ResponseEntity.created(location).body(savedClient);
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<Client> update(@PathVariable Long id, @Valid @RequestBody Client client) {
//        Optional<Client> optionalClient = clientRepository.findById(id);
//        if (!optionalClient.isPresent()) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        Client.setId(optionalClient.get().getId(id));
//        clientRepository.save(client);
//        return ResponseEntity.noContent().build();
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Client> delete(@PathVariable Long id) {
//        Optional<Client> optionalClient = clientRepository.findById(id);
//        if (!optionalClient.isPresent()) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        clientRepository.delete(optionalClient.get());
//        return ResponseEntity.noContent().build();
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<Client> getById(@PathVariable Long id) {
//        Optional<Client> optionalClient = clientRepository.findById(id);
//        if (optionalClient.isPresent()) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        return ResponseEntity.ok(optionalClient.get());
//    }
//    @GetMapping
//    public ResponseEntity<Page<Client>> getAll(Pageable pageable) {
//        return ResponseEntity.ok(clientRepository.findAll(pageable));
//    }

//    @GetMapping(value = "/{id}")
//    public ResponseEntity<Client> get(@PathVariable("id") Long id) {
//        Optional<Client> optionalClient = clientService.get(id);
//        if (!optionalClient.isPresent()) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//
//        return ResponseEntity.ok(optionalClient.get());
//    }
}