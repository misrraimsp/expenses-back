drop table if exists expense;
drop table if exists person;

drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 500 increment by 1;

create table expense (id bigint not null, amount decimal(20,3), date timestamp, description varchar(255), fk_person bigint, primary key (id));
create table person (id bigint not null, first_name varchar(255), last_name varchar(255), primary key (id));

alter table expense add constraint fk_personIdOnExpense foreign key (fk_person) references person(id);
