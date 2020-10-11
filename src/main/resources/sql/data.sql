INSERT INTO person (id,first_name,last_name) VALUES (1,'Misrraim','Suárez'),(2,'Peter','Parker'),(3,'Marco','Polo');

INSERT INTO expense (id,amount,date,description,fk_person) VALUES (1,12.1234,timestamp '2020-04-05 12:45:38','Secret Expense',1),(2,2.11,timestamp '2019-10-24 19:23:02','Old Taco',2),(3,100,timestamp '2020-09-14 23:00:19','Regalo Piñe',3),(4,23,timestamp '2020-01-17 11:42:39','Desayuno',1);
