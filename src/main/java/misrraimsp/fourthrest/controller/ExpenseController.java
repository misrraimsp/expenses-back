package misrraimsp.fourthrest.controller;

import lombok.RequiredArgsConstructor;
import misrraimsp.fourthrest.service.ExpenseServer;
import misrraimsp.fourthrest.model.dto.ExpenseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseServer expenseServer;

    @GetMapping("/expenses")
    public List<ExpenseDTO> allExpenses() {
        return expenseServer.findAll();
    }

    @PostMapping("/expenses")
    public ExpenseDTO newExpense(@RequestBody ExpenseDTO dto) {
        return expenseServer.persist(dto);
    }
}
