package misrraimsp.fourthrest.data;

import misrraimsp.fourthrest.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
