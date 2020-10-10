package misrraimsp.fourthrest.model.dto;

import misrraimsp.fourthrest.model.Expense;
import misrraimsp.fourthrest.model.Person;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExpenseConverter {

    public static ExpenseDTO convertExpenseToDto(Expense expense) {
        ExpenseDTO dto = new ExpenseDTO();
        dto.setId(expense.getId());
        dto.setPayerId(expense.getPayer().getId());
        dto.setPayerFirstName(expense.getPayer().getFirstName());
        dto.setPayerLastName(expense.getPayer().getLastName());
        dto.setDescription(expense.getDescription());
        dto.setAmount(expense.getAmount().doubleValue());
        dto.setDate(expense.getDate().toString());
        return dto;
    }

    public static Expense convertDtoToExpense(ExpenseDTO dto) {
        Expense expense = new Expense();
        Person payer = new Person();
        payer.setId(dto.getPayerId());
        expense.setPayer(payer);
        expense.setDescription(dto.getDescription());
        expense.setAmount(BigDecimal.valueOf(dto.getAmount()));
        expense.setDate(LocalDateTime.now());
        return expense;
    }
}