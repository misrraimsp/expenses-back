package misrraimsp.fourthrest.service;

import lombok.RequiredArgsConstructor;
import misrraimsp.fourthrest.data.PersonRepository;
import misrraimsp.fourthrest.model.dto.PersonConverter;
import misrraimsp.fourthrest.model.dto.PersonDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServer {

    private final PersonRepository personRepository;
    private final ExpenseServer expenseServer;

    public List<PersonDTO> findAll() {
        BigDecimal duty = this.getDuty();
        return personRepository
                .findAll()
                .stream()
                .map(person -> PersonConverter.convertPersonToDTO(person,duty))
                .collect(Collectors.toList());
    }

    private BigDecimal getDuty() {
        BigDecimal total = BigDecimal.valueOf(expenseServer.getTotalExpense());
        BigDecimal count = BigDecimal.valueOf(personRepository.count());
        return total.divide(count, RoundingMode.HALF_DOWN);
    }
}
