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
import ru.mtuci.simpleapiiuk.model.Deposit;
import ru.mtuci.simpleapiiuk.service.AccountService;
import ru.mtuci.simpleapiiuk.service.DepositService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = DepositController.class)
class DepositControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepositService depositService;

    @MockBean
    private AccountService accountService;

    private List<Deposit> depositList;

    @BeforeEach
    void setUp() {
        depositList = new ArrayList<>();
        depositList.add(new Deposit(1L, 10, 10));
        depositList.add(new Deposit(2L, 30, 30));
        depositList.add(new Deposit(3L, 13, 34));
    }

    @Test
    void getByIdDeposit() throws Exception {
        Long depositId = 1L;
        Deposit deposit = new Deposit(depositId, 10, 10);
        given(depositService.get(depositId)).willReturn(deposit);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/deposits/{id}", depositId))
                .andExpect((status().isOk()))
                .andExpect(jsonPath("$.rate", is(deposit.getRate())))
                .andExpect(jsonPath("$.term", is(deposit.getTerm())));
    }

    @Test
    void getAllDeposits() throws Exception {
        given(depositService.getAll()).willReturn(depositList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/deposits"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(depositList.size())));
    }

    @Test
    void delete() throws Exception {
        Long depositId = 1L;
        doNothing().when(depositService).delete(depositId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/deposits/{id}", depositId))
                .andExpect(status().isNoContent());

        Mockito.verify(depositService, Mockito.times(1)).delete(Mockito.eq(depositId));
    }

    @Test
    void createDeposit() throws Exception {
        Deposit deposit = new Deposit(1L, 10, 10);

        given(depositService.save(isA(Deposit.class))).willReturn(deposit);

        String json = String.format("{ \"rate\": %s, \"term\": %s }", 100, 20);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/deposits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.rate", is(deposit.getRate())))
                .andExpect(jsonPath("$.term", is(deposit.getTerm())));
    }

    @Test
    void successfulLinkDepositWithAccount() throws Exception {
        Long accountId = 1L;
        Long depositId = 2L;

        Account account = new Account(accountId, 100, 100);
        Deposit deposit = new Deposit(depositId, 10, 10);

        given(accountService.get(accountId)).willReturn(account);
        given(depositService.get(depositId)).willReturn(deposit);

        String URL = String.format("/api/v1/deposits/setAccountToDeposit?accountId=%s&depositId=%s", accountId, depositId);

        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
        );

        result.andExpect(status().isOk()).andExpect(content().string("true"));
    }

    @Test
    void failToLinkDepositWithAccountWhenAccountNotExists() throws Exception {
        Long accountId = 1L;
        Long wrongAccountId = 1000L;
        Long depositId = 2L;

        Account account = new Account(accountId, 100, 100);
        Deposit deposit = new Deposit(depositId, 10, 10);

        given(accountService.get(accountId)).willReturn(account);
        given(depositService.get(depositId)).willReturn(deposit);

        String URL = String.format("/api/v1/deposits/setAccountToDeposit?accountId=%s&depositId=%s", wrongAccountId, depositId);

        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
        );

        result.andExpect(status().isOk()).andExpect(content().string("false"));
    }

    @Test
    void failToLinkDepositWithAccountWhenDepositNotExists() throws Exception {
        Long accountId = 1L;
        Long wrongDepositId = 1000L;
        Long depositId = 2L;

        Account account = new Account(accountId, 100, 100);
        Deposit deposit = new Deposit(depositId, 10, 10);

        given(accountService.get(accountId)).willReturn(account);
        given(depositService.get(depositId)).willReturn(deposit);

        String URL = String.format("/api/v1/deposits/setAccountToDeposit?accountId=%s&depositId=%s", accountId, wrongDepositId);

        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
        );

        result.andExpect(status().isOk()).andExpect(content().string("false"));
    }    
}