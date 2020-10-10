package misrraimsp.fourthrest.util;

import misrraimsp.fourthrest.data.ExpenseRepository;
import misrraimsp.fourthrest.data.PersonRepository;
import misrraimsp.fourthrest.model.Expense;
import misrraimsp.fourthrest.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

@Configuration
public class DataLoader {
    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Bean
    CommandLineRunner initDatabase(PersonRepository personRepository, ExpenseRepository expenseRepository) {

        return args -> {
            Person peter = new Person();
            peter.setFirstName("Peter");
            peter.setLastName("Parker");
            personRepository.save(peter);

            Person marco = new Person();
            marco.setFirstName("Marco");
            marco.setLastName("Polo");
            personRepository.save(marco);

            Person paco = new Person();
            paco.setFirstName("Paco");
            paco.setLastName("Elpuro");
            personRepository.save(paco);

            Expense e1 = new Expense();
            e1.setAmount(BigDecimal.valueOf(17.75));
            e1.setPayer(peter);
            e1.setDate(LocalDateTime.of(2020, Month.APRIL,12,21,28, 54));
            e1.setDescription("Cena en McRoom");
            expenseRepository.save(e1);

            Expense e2 = new Expense();
            e2.setAmount(BigDecimal.valueOf(9.15));
            e2.setPayer(peter);
            e2.setDate(LocalDateTime.of(2020, Month.APRIL,13,8,3, 14));
            e2.setDescription("Desayuno en RealFood");
            expenseRepository.save(e2);

            Expense e3 = new Expense();
            e3.setAmount(BigDecimal.valueOf(10.5));
            e3.setPayer(paco);
            e3.setDate(LocalDateTime.of(2020, Month.APRIL,13,13,17, 0));
            e3.setDescription("Taxi");
            expenseRepository.save(e3);

            Expense e4 = new Expense();
            e4.setAmount(BigDecimal.valueOf(39.35));
            e4.setPayer(peter);
            e4.setDate(LocalDateTime.of(2020, Month.APRIL,13,15,46, 21));
            e4.setDescription("Comida en LaTurca");
            expenseRepository.save(e4);

            personRepository.findAll().forEach(person -> log.info("Loaded " + person));
            expenseRepository.findAll().forEach(expense -> log.info("Loaded " + expense));
        };
    }
}
