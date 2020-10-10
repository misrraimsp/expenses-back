package misrraimsp.fourthrest.model;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
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

    public void addExpense(Expense expense) {
        this.expenses.add(expense);
        expense.setPayer(this);
    }

    public BigDecimal getBalance(BigDecimal duty) {
        return this.getTotalExpense().subtract(duty);
    }

    private BigDecimal getTotalExpense() {
        return this.expenses
                .stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
