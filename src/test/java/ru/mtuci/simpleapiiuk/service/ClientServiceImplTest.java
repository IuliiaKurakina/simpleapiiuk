package ru.mtuci.simpleapiiuk.service;

// import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import ru.mtuci.simpleapiiuk.dao.ClientRepository;
import ru.mtuci.simpleapiiuk.model.Client;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClientServiceImplTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClientRepository repository;

    @Test
    @Rollback(false)
    public void it_correctly_loads_client() {
        Client expectedClient = new Client(1, "4614", "Фамилия", "Имя", "79265558877");
        System.out.println(expectedClient);
        entityManager.persist(expectedClient);
        Client actualClient = repository.getOne(1L);

        assertEquals(expectedClient.toString(), actualClient.toString(), "Восстановленный клиент не совпадает с сохраненным");
    }
}
