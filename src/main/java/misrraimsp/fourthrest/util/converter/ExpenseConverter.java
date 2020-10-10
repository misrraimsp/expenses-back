package misrraimsp.fourthrest.util.converter;

import misrraimsp.fourthrest.model.Expense;
import misrraimsp.fourthrest.util.dto.ExpenseDTO;

public class ExpenseConverter {

    public static ExpenseDTO convertExpenseToDTO(Expense expense) {
        ExpenseDTO dto = new ExpenseDTO();
        dto.setPayerFirstName(expense.getPayer().getFirstName());
        dto.setPayerLastName(expense.getPayer().getLastName());
        dto.setDescription(expense.getDescription());
        dto.setAmount(expense.getAmount().doubleValue());
        dto.setDate(expense.getDate().toString());
        return dto;
    }
}
