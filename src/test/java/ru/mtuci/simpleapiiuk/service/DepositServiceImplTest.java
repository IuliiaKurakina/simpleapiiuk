package ru.mtuci.simpleapiiuk.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mtuci.simpleapiiuk.dao.DepositRepository;
import ru.mtuci.simpleapiiuk.model.Deposit;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DepositServiceImplTest {

    private DepositServiceImpl sut;
    private DepositRepository mockedRepository;

    @BeforeEach
    void setUp() {
        mockedRepository = Mockito.mock(DepositRepository.class);
        sut = new DepositServiceImpl(mockedRepository);
    }

    @Test
    void get() {
        Deposit fakeDeposit = new Deposit(1L, 2, 2);
        Optional<Deposit> expected = Optional.of(fakeDeposit);

        Mockito.when(mockedRepository.findById(1L)).thenReturn(expected);

        Assertions.assertEquals(sut.get(1L), fakeDeposit);
    }

    @Test
    void getAll() {
        Deposit[] fakeDeposit = {
                new Deposit(1L, 2, 2)
        };
        List<Deposit> expected = Arrays.asList(fakeDeposit);
        Mockito.when(mockedRepository.findAll()).thenReturn(expected);

        Assertions.assertEquals(sut.getAll(), expected);
    }

    @Test
    void save() {
        Deposit fakeDeposit = new Deposit(1L, 2, 2);

        Mockito.when(mockedRepository.save(fakeDeposit)).thenReturn(fakeDeposit);

        Assertions.assertEquals(sut.save(fakeDeposit), fakeDeposit);
    }

    @Test
    void delete() {
        sut.delete(1L);
        Mockito.verify(mockedRepository, Mockito.times(1)).delete(Mockito.eq(1L));
    }
}