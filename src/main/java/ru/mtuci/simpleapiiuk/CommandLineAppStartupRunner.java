package ru.mtuci.simpleapiiuk;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Component;
        import ru.mtuci.simpleapiiuk.dao.ClientRepository;

@Component
public class CommandLineAppStartupRunner {

    private final ClientRepository clientRepository;

    @Autowired
    public CommandLineAppStartupRunner(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void run(String...args) throws Exception {
        //System.out.println(clientRepository.findAll());
    }
}
