package ru.mtuci.simpleapiiuk.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mtuci.simpleapiiuk.dao.ClientRepository;
import ru.mtuci.simpleapiiuk.model.Client;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    private ClientServiceImpl sut;
    private ClientRepository mockedRepository;

    @BeforeEach
    void setUp() {
        mockedRepository = Mockito.mock(ClientRepository.class);
        sut = new ClientServiceImpl(mockedRepository);
    }

    @Test
    void get() {
        Client fakeClient = new Client(1L, "Тест", "900");
        Optional<Client> expectedOptionalFakeClient = Optional.of(fakeClient);

        Mockito.when(mockedRepository.findById(1L)).thenReturn(expectedOptionalFakeClient);

        Assertions.assertEquals(fakeClient, sut.get(1L));
    }

    @Test
    void getAll() {
        Client[] fakeClients = {
                new Client(1L, "Тест","900")
        };
        List<Client> expectedClientsList = Arrays.asList(fakeClients);
        Mockito.when(mockedRepository.findAll()).thenReturn(expectedClientsList);

        Assertions.assertEquals(sut.getAll(), expectedClientsList);
    }

    @Test
    void save() {
        Client fakeClient = new Client(1L, "Тест", "900");

        Mockito.when(mockedRepository.save(fakeClient)).thenReturn(fakeClient);

        Assertions.assertEquals(sut.save(fakeClient), fakeClient);
    }

    @Test
    void delete() {
        sut.delete(1L);
        Mockito.verify(mockedRepository, Mockito.times(1)).delete(Mockito.eq(1L));
    }
}