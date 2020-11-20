package ru.mtuci.simpleapiiuk.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.mtuci.simpleapiiuk.model.Client;
import ru.mtuci.simpleapiiuk.service.ClientService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
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
        clientList = new ArrayList<>();
        clientList.add(new Client(1L, "Тест", "900"));
        clientList.add(new Client(2L, "Тестёночек", "800"));
        clientList.add(new Client(3L, "Тестюшка", "700"));
    }

    @Test
    void getByIdClient() throws Exception {
        Long clientId = 1L;
        Client client = new Client(clientId, "Тест", "900");
        given(clientService.get(clientId)).willReturn(client);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients/{id}", clientId))
                .andExpect((status().isOk()))
                .andExpect(jsonPath("$.name", is(client.getName())))
                .andExpect(jsonPath("$.phone", is(client.getPhone())));
    }

    @Test
    void getAllClients() throws Exception {
        given(clientService.getAll()).willReturn(clientList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(clientList.size())));
    }


    @Test
    void createClient() throws Exception {
        Client client = new Client(1L, "Громкий дворник с лопатой бесит", "7903548878");

        given(clientService.save(isA(Client.class))).willReturn(client);

        String json = "{" +
                "\"name\": \"Громкий дворник с лопатой бесит\"," +
                "\"phone\": \"7903548878\"" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(client.getName())))
                .andExpect(jsonPath("$.phone", is(client.getPhone())));
    }

    @Test
    void deleteClient() throws Exception {
        Long clientId = 1L;
        doNothing().when(clientService).delete(clientId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/clients/{id}", clientId))
                .andExpect(status().isNoContent());

        Mockito.verify(clientService, Mockito.times(1)).delete(Mockito.eq(clientId));
    }
}