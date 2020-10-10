package misrraimsp.fourthrest.model.dto;

import lombok.Data;

@Data
public class ExpenseDTO {

    private Long id;

    private Long payerId;

    private String payerFirstName;

    private String payerLastName;

    private double amount;

    private String description;

    private String date;
}
