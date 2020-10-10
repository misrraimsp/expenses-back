package misrraimsp.fourthrest.model.dto;

import lombok.Data;

@Data
public class PersonDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private double balance;
}
