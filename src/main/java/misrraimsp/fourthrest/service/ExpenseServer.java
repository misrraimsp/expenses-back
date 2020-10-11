package misrraimsp.fourthrest.service;

import lombok.RequiredArgsConstructor;
import misrraimsp.fourthrest.data.ExpenseRepository;
import misrraimsp.fourthrest.data.PersonRepository;
import misrraimsp.fourthrest.model.Expense;
import misrraimsp.fourthrest.model.Person;
import misrraimsp.fourthrest.model.dto.ExpenseConverter;
import misrraimsp.fourthrest.model.dto.ExpenseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServer {

    private final ExpenseRepository expenseRepository;
    private final PersonRepository personRepository;

    public List<ExpenseDTO> findAll() {
        return expenseRepository
                .findAll()
                .stream()
                .map(ExpenseConverter::convertExpenseToDto)
                .collect(Collectors.toList());
    }

    public ExpenseDTO persist(ExpenseDTO dto) {
        Person payer = personRepository.findById(dto.getPayerId()).orElseThrow(RuntimeException::new);
        Expense expense = ExpenseConverter.convertDtoToExpense(dto);
        payer.addExpense(expense);
        return ExpenseConverter.convertExpenseToDto(expenseRepository.save(expense));
    }
}
