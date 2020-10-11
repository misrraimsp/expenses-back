package misrraimsp.fourthrest.service;

import lombok.RequiredArgsConstructor;
import misrraimsp.fourthrest.data.ExpenseRepository;
import misrraimsp.fourthrest.model.Expense;
import misrraimsp.fourthrest.model.dto.ExpenseConverter;
import misrraimsp.fourthrest.model.dto.ExpenseDTO;
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
                .map(ExpenseConverter::convertExpenseToDto)
                .collect(Collectors.toList());
    }

    public double getTotalExpense() {
        return expenseRepository.getTotalExpense();
    }

    public ExpenseDTO persist(ExpenseDTO dto) {
        Expense saved = expenseRepository.save(ExpenseConverter.convertDtoToExpense(dto));
        return ExpenseConverter.convertExpenseToDto(expenseRepository.findById(saved.getId()).orElse(saved));
    }
}
