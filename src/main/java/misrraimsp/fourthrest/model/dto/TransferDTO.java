package misrraimsp.fourthrest.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferDTO {

    private String issuerFirstName;
    private String issuerLastName;
    private String recipientFirstName;
    private String recipientLastName;
    private BigDecimal amount;

}
