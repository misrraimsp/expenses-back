package misrraimsp.fourthrest.data;

import misrraimsp.fourthrest.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT SUM(amount) FROM expense;"
    )
    double getTotalExpense();
}
