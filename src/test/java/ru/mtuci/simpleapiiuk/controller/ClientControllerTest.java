package ru.mtuci.simpleapiiuk.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.mtuci.simpleapiiuk.model.Client;
import ru.mtuci.simpleapiiuk.service.ClientService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClientController.class)
class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    private List<Client> clientList;

    @BeforeEach
    void setUp() {
        this.clientList = new ArrayList<>();
        this.clientList.add(new Client(4614, "800706", "Петоров", "Иван", "79260001124"));
        this.clientList.add(new Client(4610, "800701", "Сидорова", "Ирина", "79260001121"));
        this.clientList.add(new Client(4614, "800708", "Михайлов", "Петр", "79260001126"));
    }

    @Test
    void itGetsAll() throws Exception {
        given(clientService.getAll()).willReturn(clientList);

        this.mockMvc.perform(get("/api/v1/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(clientList.size())));
    }

    @Test
    void itGets() throws Exception {
        final Long clientId = 1L;
        final Client client = new Client(4614, "800708", "Михайлов", "Петр", "79260001126");
        given(clientService.get(clientId)).willReturn(client);

        this.mockMvc.perform(get("/api/v1/clients/{id}", clientId))
                .andExpect((status().isOk()))
                .andExpect(jsonPath("$.surname", is(client.getSurname())))
                .andExpect(jsonPath("$.name", is(client.getName())))
                .andExpect(jsonPath("$.phone", is(client.getPhone())))
                .andExpect(jsonPath("$.serial", is(client.getSerial())))
                .andExpect(jsonPath("$.number", is(client.getNumber())));
    }

    @Test
    void itReturnsValidStatusWhenDeletion() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/clients/{id}", "1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void itSaves() throws Exception {
        given(clientService.save(any(Client.class))).willAnswer((invocation) -> invocation.getArgument(0));
        final Client client = new Client(4614, "800708", "Михайлов", "Петр", "79260001126");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"serial\": 4614," +
                        "\"number\": \"800708\"," +
                        "\"surname\": \"Михайлов\"," +
                        "\"name\": \"Петр\"," +
                        "\"phone\": \"79260001126\"" +
                        "}"))
                .andExpect(jsonPath("$.surname", is(client.getSurname())))
                .andExpect(jsonPath("$.name", is(client.getName())))
                .andExpect(jsonPath("$.phone", is(client.getPhone())))
                .andExpect(jsonPath("$.serial", is(client.getSerial())))
                .andExpect(jsonPath("$.number", is(client.getNumber())));
    }
}