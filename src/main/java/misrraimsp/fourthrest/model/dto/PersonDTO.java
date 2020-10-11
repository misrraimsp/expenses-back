package misrraimsp.fourthrest.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PersonDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal balance;
}
