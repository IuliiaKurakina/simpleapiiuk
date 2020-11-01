package ru.mtuci.simpleapiiuk.service;
        import ru.mtuci.simpleapiiuk.model.Client;
        import java.util.List;

public interface ClientService {
    Client get(Long id);

    List<Client> getAll();

    Client save(Client client);

    void delete(Long id);
}
