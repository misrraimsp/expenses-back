package misrraimsp.fourthrest.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Expense {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Person payer;

    private BigDecimal amount;

    private String description;

    private LocalDateTime date;
}
