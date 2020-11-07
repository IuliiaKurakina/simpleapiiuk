package ru.mtuci.simpleapiiuk.service;

import ru.mtuci.simpleapiiuk.model.Account;

import java.util.List;

public interface AccountService {
    Account get(Long id);

    List<Account> getAll();

    Account save(Account account);

    void delete(Long id);
}
