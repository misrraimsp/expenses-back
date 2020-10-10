package misrraimsp.fourthrest.model.dto;

import misrraimsp.fourthrest.model.Person;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PersonConverter {

    public static PersonDTO convertPersonToDto(Person person, BigDecimal duty) {
        PersonDTO dto = new PersonDTO();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setBalance(person.getBalance(duty).doubleValue());
        return dto;
    }

    public static Person convertDtoToPerson(PersonDTO dto) {
        Person person = new Person();
        person.setExpenses(new ArrayList<>());
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        return person;
    }
}
