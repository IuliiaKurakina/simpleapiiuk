package ru.mtuci.simpleapiiuk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtuci.simpleapiiuk.dao.DepositRepository;
import ru.mtuci.simpleapiiuk.model.Deposit;

import java.util.List;

@Service
public class DepositServiceImpl implements DepositService {

    private final DepositRepository currentRepository;

    @Autowired
    public DepositServiceImpl(DepositRepository repository) {
        this.currentRepository = repository;
    }

    @Override
    public Deposit get(Long id) {
        return currentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Deposit> getAll() {
        return currentRepository.findAll();
    }

    @Override
    public Deposit save(Deposit deposit) {
        return currentRepository.save(deposit);
    }

    @Override
    public void delete(Long id) {
        currentRepository.delete(id);
    }
}
