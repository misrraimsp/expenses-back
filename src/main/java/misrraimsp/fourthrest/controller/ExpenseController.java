package misrraimsp.fourthrest.controller;

import lombok.RequiredArgsConstructor;
import misrraimsp.fourthrest.service.ExpenseServer;
import misrraimsp.fourthrest.model.dto.ExpenseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
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
