package misrraimsp.fourthrest.service;

import lombok.RequiredArgsConstructor;
import misrraimsp.fourthrest.data.PersonRepository;
import misrraimsp.fourthrest.model.Person;
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
                .map(person -> PersonConverter.convertPersonToDto(person,duty))
                .collect(Collectors.toList());
    }

    public PersonDTO persist(PersonDTO dto) {
        Person saved = personRepository.save(PersonConverter.convertDtoToPerson(dto));
        BigDecimal duty = this.getDuty();
        return PersonConverter.convertPersonToDto(saved,duty);
    }

    private BigDecimal getDuty() {
        BigDecimal total = BigDecimal.valueOf(expenseServer.getTotalExpense());
        BigDecimal count = BigDecimal.valueOf(personRepository.count());
        return total.divide(count, RoundingMode.HALF_DOWN);
    }
}
