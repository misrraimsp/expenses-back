package misrraimsp.fourthrest.data;

import misrraimsp.fourthrest.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
}
