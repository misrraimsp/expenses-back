package misrraimsp.fourthrest.service;

import lombok.RequiredArgsConstructor;
import misrraimsp.fourthrest.data.ExpenseRepository;
import misrraimsp.fourthrest.util.converter.ExpenseConverter;
import misrraimsp.fourthrest.util.dto.ExpenseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServer {

    private final ExpenseRepository expenseRepository;

    public List<ExpenseDTO> findAll() {
        return expenseRepository
                .findAll()
                .stream()
                .map(ExpenseConverter::convertExpenseToDTO)
                .collect(Collectors.toList());
    }

    public double getTotalExpense() {
        return expenseRepository.getTotalExpense();
    }
}
