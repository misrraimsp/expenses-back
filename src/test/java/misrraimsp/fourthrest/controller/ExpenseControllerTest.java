package misrraimsp.fourthrest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import misrraimsp.fourthrest.model.dto.ExpenseDTO;
import misrraimsp.fourthrest.service.ExpenseServer;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExpenseController.class)
class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ExpenseServer expenseServer;

    private ExpenseDTO buildExpenseDTO() {
        ExpenseDTO expenseDTO = new ExpenseDTO();
        expenseDTO.setId(1L);
        expenseDTO.setPayerId(1L);
        expenseDTO.setPayerFirstName("Peter");
        expenseDTO.setPayerLastName("Parker");
        expenseDTO.setDescription("Gasto Prueba");
        expenseDTO.setAmount(BigDecimal.valueOf(1));
        expenseDTO.setDate(LocalDateTime.of(2020, Month.OCTOBER,4,14,0, 0).toString());

        /*
        Person payer = new Person();
        payer.setId(expenseDTO.getPayerId());
        payer.setFirstName("Peter");
        payer.setLastName("Parker");

        Expense expense = new Expense();
        expense.setId(1L);
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(LocalDateTime.now());
        expense.setDescription(expenseDTO.getDescription());

        payer.addExpense(expense);
        */

        return expenseDTO;
    }

    @Test
    public void allExpenses_whenValidMethodAndUrl_thenReturns200() throws Exception {
        mockMvc.perform(get("/expenses"))
                .andExpect(status().isOk());
    }

    @Test
    public void allExpenses_whenCorrectlyInvoked_thenCallsExpenseServerFindAll() throws Exception {
        // given
        // ...
        // when
        mockMvc.perform(get("/expenses"));
        // then
        verify(expenseServer, times(1)).findAll();
    }

    @Test
    public void newExpense_whenValidMethodAndUrlAndRequestBody_thenReturns200() throws Exception {
        mockMvc.perform(post("/expenses")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new ExpenseDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void newExpense_whenInvalidRequestBody_thenReturns400() throws Exception {
        // given
        // ...
        // when
        ResultActions resultActions = mockMvc.perform(post("/expenses"));
        // then
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    public void newExpense_whenCorrectlyInvoked_thenCallsExpenseServerPersist() throws Exception {
        // given
        ExpenseDTO expenseDTO = new ExpenseDTO();
        // when
        mockMvc.perform(post("/expenses")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(expenseDTO)));
        // then
        ArgumentCaptor<ExpenseDTO> expenseDTOArgumentCaptor = ArgumentCaptor.forClass(ExpenseDTO.class);
        verify(expenseServer, times(1)).persist(expenseDTOArgumentCaptor.capture());
        assertThat(expenseDTOArgumentCaptor.getValue()).isEqualTo(expenseDTO);
    }

    @Test
    void newExpense_whenCorrectlyInvoked_thenReturnsTheSavedExpense() throws Exception {
        // given
        ExpenseDTO expenseDTO = this.buildExpenseDTO();
        given(expenseServer.persist(any(ExpenseDTO.class))).willReturn(expenseDTO);
        // when
        ResultActions resultActions = mockMvc.perform(post("/expenses")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(expenseDTO)));
        // then
        resultActions.andExpect(jsonPath("id").value(expenseDTO.getId()));
        resultActions.andExpect(jsonPath("payerId").value(expenseDTO.getPayerId()));
        resultActions.andExpect(jsonPath("payerFirstName").value(expenseDTO.getPayerFirstName()));
        resultActions.andExpect(jsonPath("payerLastName").value(expenseDTO.getPayerLastName()));
        resultActions.andExpect(jsonPath("amount").value(expenseDTO.getAmount()));
        resultActions.andExpect(jsonPath("description").value(expenseDTO.getDescription()));
        resultActions.andExpect(jsonPath("date").value(expenseDTO.getDate()));
    }
}
