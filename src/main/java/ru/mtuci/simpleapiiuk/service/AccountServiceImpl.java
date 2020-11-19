package ru.mtuci.simpleapiiuk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtuci.simpleapiiuk.dao.AccountRepository;
import ru.mtuci.simpleapiiuk.model.Account;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository currentRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        this.currentRepository = repository;
    }

    @Override
    public Account get(Long id) {
        return currentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Account> getAll() {
        return currentRepository.findAll();
    }

    @Override
    public Account save(Account account) {
        return currentRepository.save(account);
    }

    @Override
    public void delete(Long id) {
        currentRepository.delete(id);
    }
}
