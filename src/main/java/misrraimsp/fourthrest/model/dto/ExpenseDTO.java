package misrraimsp.fourthrest.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpenseDTO {

    private Long id;
    private Long payerId;
    private String payerFirstName;
    private String payerLastName;
    private BigDecimal amount;
    private String description;
    private String date;
}
