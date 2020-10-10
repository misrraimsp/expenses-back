package misrraimsp.fourthrest.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

}
