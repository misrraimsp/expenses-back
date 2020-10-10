package misrraimsp.fourthrest.controller;

import lombok.RequiredArgsConstructor;
import misrraimsp.fourthrest.service.ExpenseServer.ExpenseServer;
import misrraimsp.fourthrest.util.dto.ExpenseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseServer expenseServer;


    /*
    @GetMapping("/expenses")
    public ResponseEntity<List<Expense>> allExpenses() {
        return ResponseEntity.ok(expenseServer.findAll());
    }
    */

    @GetMapping("/expenses")
    public List<ExpenseDTO> allExpenses() {
        return expenseServer.findAll();
    }
}
