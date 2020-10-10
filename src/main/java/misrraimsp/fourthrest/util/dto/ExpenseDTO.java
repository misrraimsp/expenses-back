package misrraimsp.fourthrest.util.dto;

import lombok.Data;

@Data
public class ExpenseDTO {

    private String payerFirstName;

    private String payerLastName;

    private double amount;

    private String description;

    private String date;
}
