package misrraimsp.fourthrest.model.dto;

import misrraimsp.fourthrest.model.Person;

import java.math.BigDecimal;

public class PersonConverter {

    public static PersonDTO convertPersonToDTO(Person person, BigDecimal duty) {
        PersonDTO dto = new PersonDTO();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setBalance(person.getBalance(duty).doubleValue());
        return dto;
    }
}
