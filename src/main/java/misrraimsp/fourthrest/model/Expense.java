package misrraimsp.fourthrest.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Expense {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_person")
    private Person payer;

    private BigDecimal amount;

    private String description;

    private LocalDateTime date;
}
