package ru.mtuci.simpleapiiuk.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.mtuci.simpleapiiuk.model.Account;
import ru.mtuci.simpleapiiuk.model.Client;
import ru.mtuci.simpleapiiuk.service.AccountService;
import ru.mtuci.simpleapiiuk.service.ClientService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private ClientService clientService;

    private List<Account> accountList;

    @BeforeEach
    void setUp() {
        accountList = new ArrayList<>();
        accountList.add(new Account(1L, 100, 100));
        accountList.add(new Account(2L, 300, 300));
        accountList.add(new Account(3L, 103, 340));
    }

    @Test
    void getByIdAccount() throws Exception {
        Long accountId = 1L;
        Account account = new Account(accountId, 100, 100);
        given(accountService.get(accountId)).willReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/accounts/{id}", accountId))
                .andExpect((status().isOk()))
                .andExpect(jsonPath("$.number", is(account.getNumber())))
                .andExpect(jsonPath("$.amount", is(account.getAmount())));
    }

    @Test
    void getAllAccounts() throws Exception {
        given(accountService.getAll()).willReturn(accountList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(accountList.size())));
    }

    @Test
    void delete() throws Exception {
        Long accountId = 1L;
        doNothing().when(accountService).delete(accountId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/accounts/{id}", accountId))
                .andExpect(status().isNoContent());

        Mockito.verify(accountService, Mockito.times(1)).delete(Mockito.eq(accountId));
    }

    @Test
    void createAccount() throws Exception {
        Account account = new Account(1L, 100, 100);

        given(accountService.save(isA(Account.class))).willReturn(account);

        String json = "{" +
                "\"number\": 100," +
                "\"amount\": 100" +
                "}";

        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(account.getNumber())))
                .andExpect(jsonPath("$.amount", is(account.getAmount())));
    }

    @Test
    void successfulLinkAccountWithClient() throws Exception {
        Long clientId = 1L;
        Long accountId = 2L;

        Client client = new Client(clientId, "Ефремов", "8 (495) 450-87-78");
        Account account = new Account(accountId, 100, 100);

        given(clientService.get(clientId)).willReturn(client);
        given(accountService.get(accountId)).willReturn(account);

        String URL = String.format("/api/v1/accounts/setClientToAccount?clientId=%s&accountId=%s", clientId, accountId);

        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
        );

        result.andExpect(status().isOk()).andExpect(content().string("true"));
    }

    @Test
    void failToLinkAccountWithClientWhenClientNotExists() throws Exception {
        Long clientId = 1L;
        Long wrongClientId = 1000L;
        Long accountId = 2L;

        Client client = new Client(clientId, "Ефремов", "8 (495) 450-87-78");
        Account account = new Account(accountId, 100, 100);

        given(clientService.get(clientId)).willReturn(client);
        given(accountService.get(accountId)).willReturn(account);

        String URL = String.format("/api/v1/accounts/setClientToAccount?clientId=%s&accountId=%s", wrongClientId, accountId);

        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
        );

        result.andExpect(status().isOk()).andExpect(content().string("false"));
    }

    @Test
    void failToLinkAccountWithClientWhenAccountNotExists() throws Exception {
        Long clientId = 1L;
        Long wrongAccountId = 1000L;
        Long accountId = 2L;

        Client client = new Client(clientId, "Ефремов", "8 (495) 450-87-78");
        Account account = new Account(accountId, 100, 100);

        given(clientService.get(clientId)).willReturn(client);
        given(accountService.get(accountId)).willReturn(account);

        String URL = String.format("/api/v1/accounts/setClientToAccount?clientId=%s&accountId=%s", clientId, wrongAccountId);

        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
        );

        result.andExpect(status().isOk()).andExpect(content().string("false"));
    }
}