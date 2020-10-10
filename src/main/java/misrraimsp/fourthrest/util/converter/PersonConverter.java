package misrraimsp.fourthrest.util.converter;

import misrraimsp.fourthrest.model.Person;
import misrraimsp.fourthrest.util.dto.PersonDTO;

import java.math.BigDecimal;

public class PersonConverter {

    public static PersonDTO convertPersonToDTO(Person person, BigDecimal duty) {
        PersonDTO dto = new PersonDTO();
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setBalance(person.getBalance(duty).doubleValue());
        return dto;
    }
}
