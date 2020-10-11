package misrraimsp.fourthrest.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferDTO {

    private PersonDTO issuer;
    private PersonDTO recipient;
    private BigDecimal amount;

}
