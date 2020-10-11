package misrraimsp.fourthrest.service;

import lombok.RequiredArgsConstructor;
import misrraimsp.fourthrest.data.ExpenseRepository;
import misrraimsp.fourthrest.data.PersonRepository;
import misrraimsp.fourthrest.model.Person;
import misrraimsp.fourthrest.model.dto.PersonConverter;
import misrraimsp.fourthrest.model.dto.PersonDTO;
import misrraimsp.fourthrest.model.dto.TransferDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServer {

    private final PersonRepository personRepository;
    private final ExpenseRepository expenseRepository;

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
        return PersonConverter.convertPersonToDto(saved, this.getDuty());
    }

    public List<TransferDTO> getTransfers() {
        Stack<PersonDTO> debtors = new Stack<>();
        Stack<PersonDTO> creditors = new Stack<>();
        this.findAll().forEach(personDTO -> {
            if (personDTO.getBalance().compareTo(BigDecimal.ZERO) < 0) debtors.push(personDTO);
            if (personDTO.getBalance().compareTo(BigDecimal.ZERO) > 0) creditors.push(personDTO);
        });

        List<TransferDTO> transfers = new ArrayList<>();
        while (!debtors.empty() && !creditors.empty()) {
            PersonDTO debtor = debtors.peek();
            PersonDTO creditor = creditors.peek();

            TransferDTO transferDTO = new TransferDTO();
            transferDTO.setIssuer(debtor);
            transferDTO.setRecipient(creditor);

            BigDecimal result = debtor.getBalance().add(creditor.getBalance());
            if (result.compareTo(BigDecimal.ZERO) < 0) {
                transferDTO.setAmount(creditor.getBalance());
                debtor.setBalance(result);
                creditors.pop();
            } else if (result.compareTo(BigDecimal.ZERO) > 0) {
                transferDTO.setAmount(debtor.getBalance().abs());
                creditor.setBalance(result);
                debtors.pop();
            } else {
                transferDTO.setAmount(creditor.getBalance());
                debtors.pop();
                creditors.pop();
            }

            transfers.add(transferDTO);
        }

        return transfers;
    }

    private BigDecimal getDuty() {
        BigDecimal total = BigDecimal.valueOf(expenseRepository.getTotalExpense()).setScale(3, RoundingMode.HALF_UP);
        BigDecimal count = BigDecimal.valueOf(personRepository.count()).setScale(3, RoundingMode.HALF_UP);
        return total.divide(count, 3, RoundingMode.HALF_UP);
    }
}
