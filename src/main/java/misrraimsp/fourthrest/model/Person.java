package misrraimsp.fourthrest.model;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "payer")
    private List<Expense> expenses = new ArrayList<>();

    private String firstName;

    private String lastName;

    public BigDecimal getBalance(BigDecimal duty) {
        return this.getTotalExpense().subtract(duty).setScale(3, RoundingMode.HALF_UP);
    }

    public void addExpense(Expense expense) {
        this.expenses.add(expense);
        expense.setPayer(this);
    }

    private BigDecimal getTotalExpense() {
        return this.expenses
                .stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(3, RoundingMode.HALF_UP);
    }

}
