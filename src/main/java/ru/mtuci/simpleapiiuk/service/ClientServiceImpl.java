package ru.mtuci.simpleapiiuk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtuci.simpleapiiuk.dao.ClientRepository;
import ru.mtuci.simpleapiiuk.model.Client;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository currentRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository repository) {
        this.currentRepository = repository;
    }

    @Override
    public Client get(Long id) {
        return currentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Client> getAll() {
        return currentRepository.findAll();
    }

    @Override
    public Client save(Client client) {
        return currentRepository.save(client);
    }

    @Override
    public void delete(Long id) {
        currentRepository.delete(id);
    }
}
