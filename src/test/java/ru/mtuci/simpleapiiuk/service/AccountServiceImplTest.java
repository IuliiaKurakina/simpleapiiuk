package ru.mtuci.simpleapiiuk.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mtuci.simpleapiiuk.dao.AccountRepository;
import ru.mtuci.simpleapiiuk.model.Account;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    private AccountServiceImpl sut;
    private AccountRepository mockedRepository;

    @BeforeEach
    void setUp() {
        mockedRepository = Mockito.mock(AccountRepository.class);
        sut = new AccountServiceImpl(mockedRepository);
    }

    @Test
    void get() {
        Account fakeAccount = new Account(1L, 800, 800);
        Optional<Account> expected = Optional.of(fakeAccount);

        Mockito.when(mockedRepository.findById(1L)).thenReturn(expected);

        Assertions.assertEquals(sut.get(1L), fakeAccount);
    }

    @Test
    void getAll() {
        Account[] fakeAccount = {
                new Account(1L, 800, 800)
        };
        List<Account> expected = Arrays.asList(fakeAccount);
        Mockito.when(mockedRepository.findAll()).thenReturn(expected);

        Assertions.assertEquals(sut.getAll(), expected);
    }

    @Test
    void save() {
        Account fakeAccount = new Account(1L, 800, 800);

        Mockito.when(mockedRepository.save(fakeAccount)).thenReturn(fakeAccount);

        Assertions.assertEquals(sut.save(fakeAccount), fakeAccount);
    }

    @Test
    void delete() {
        sut.delete(1L);
        Mockito.verify(mockedRepository, Mockito.times(1)).delete(Mockito.eq(1L));
    }
}