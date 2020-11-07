package ru.mtuci.simpleapiiuk.service;

import ru.mtuci.simpleapiiuk.model.Deposit;

import java.util.List;

public interface DepositService {
    Deposit get(Long id);

    List<Deposit> getAll();

    Deposit save(Deposit client);

    void delete(Long id);
}